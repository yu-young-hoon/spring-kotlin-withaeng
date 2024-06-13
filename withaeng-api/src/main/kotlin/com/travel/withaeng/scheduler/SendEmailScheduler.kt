package com.travel.withaeng.scheduler

import com.travel.withaeng.common.Constants.Url.VALIDATING_EMAIL_URL
import com.travel.withaeng.domain.validateemail.ValidatingEmailService
import com.travel.withaeng.domain.validateemail.ValidatingEmailStatus
import com.travel.withaeng.external.ses.MailSender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class SendEmailScheduler(
    private val validatingEmailService: ValidatingEmailService,
    private val mailSender: MailSender
) {

    private val log: Logger = LoggerFactory.getLogger(SendEmailScheduler::class.java)

    @Scheduled(cron = "0 * * * * *")
    @Async("asyncSchedulerExecutor")
    @Transactional
    fun sendEmail() {
        val now = LocalDateTime.now()
        log.info("Start sending emails at {}", now)
        val willSendMails = validatingEmailService.findAllByStatusNot(ValidatingEmailStatus.DONE)
        willSendMails.forEach { emailDto ->
            val to = emailDto.email
            val validatingEmailUrl = "$VALIDATING_EMAIL_URL?code=${emailDto.code}&email=${to}"
            mailSender.sendValidatingEmail(validatingEmailUrl, to)
        }
        if (willSendMails.isNotEmpty()) {
            validatingEmailService.updateStatusByIds(willSendMails.map { it.id }.toSet(), ValidatingEmailStatus.DONE)
        }
    }
}
