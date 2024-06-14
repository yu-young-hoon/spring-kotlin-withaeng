package com.travel.withaeng.scheduler

import com.travel.withaeng.domain.validateemail.ValidatingEmailService
import com.travel.withaeng.domain.validateemail.ValidatingEmailStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class DeleteValidatingEmailScheduler(
    private val validatingEmailService: ValidatingEmailService
) {

    private val log: Logger = LoggerFactory.getLogger(DeleteValidatingEmailScheduler::class.java)

    @Scheduled(cron = "0 * * * * *")
    @Async("asyncSchedulerExecutor")
    @Transactional
    fun sendEmail() {
        val now = LocalDateTime.now()
        log.info("Start deleting validating emails at {}", now)
        val willDeleteEmails = validatingEmailService.findAllByStatusNot(ValidatingEmailStatus.YET)
            .filter { emailDto -> emailDto.createdAt.plusMinutes(VALIDATING_MAIL_EXPIRED_MINUTES) < now }
            .map { emailDto -> emailDto.id }
            .toSet()
        if (willDeleteEmails.isNotEmpty()) {
            validatingEmailService.deleteAllById(willDeleteEmails)
            log.info("End deleteing {} validating emails at {}", willDeleteEmails.size, LocalDateTime.now())
        }
    }

    companion object {
        private const val VALIDATING_MAIL_EXPIRED_MINUTES = 5L
    }
}
