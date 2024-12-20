package com.withaeng.api.scheduler

import com.withaeng.domain.verificationemail.VerificationEmailService
import com.withaeng.domain.verificationemail.VerificationEmailStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class DeleteVerificationEmailScheduler(
    private val verificationEmailService: VerificationEmailService,
) {
    companion object {
        private const val VERIFICATION_EMAIL_EXPIRED_MINUTES = 5L
    }

    private val log: Logger = LoggerFactory.getLogger(DeleteVerificationEmailScheduler::class.java)

//    @Scheduled(cron = "0 * * * * *")
    @Async("asyncSchedulerExecutor")
    @Transactional
    fun deleteEmails() {
        val now = LocalDateTime.now()
        log.info("Start deleting verification emails at {}", now)
        val willDeleteEmails = verificationEmailService.findAllByStatusNot(VerificationEmailStatus.YET)
            .filter { emailDto -> emailDto.createdAt.plusMinutes(VERIFICATION_EMAIL_EXPIRED_MINUTES) < now }
            .map { emailDto -> emailDto.id }
            .toSet()
        if (willDeleteEmails.isNotEmpty()) {
            verificationEmailService.deleteAllById(willDeleteEmails)
            log.info("End deleting {} verification emails at {}", willDeleteEmails.size, LocalDateTime.now())
        }
    }
}
