package com.travel.withaeng.scheduler

import com.travel.withaeng.common.Constants.Url.CHANGING_PASSWORD_EMAIL_URL
import com.travel.withaeng.common.Constants.Url.VALIDATING_EMAIL_URL
import com.travel.withaeng.domain.validateemail.ValidatingEmailService
import com.travel.withaeng.domain.validateemail.ValidatingEmailStatus
import com.travel.withaeng.domain.validateemail.ValidatingEmailType
import com.travel.withaeng.external.ses.MailSender
import com.travel.withaeng.external.ses.MailType
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
            val code = emailDto.code
            val type = emailDto.type
            val redirectUrl = "${type.toValidatingUrl()}?code=$code&email=$to"
            mailSender.send(redirectUrl, to, emailDto.type.toMailType())
        }
        if (willSendMails.isNotEmpty()) {
            val updateCount = validatingEmailService.updateStatusByIds(
                willSendMails.map { it.id }.toSet(),
                ValidatingEmailStatus.DONE
            )
            log.info("End sending {} emails at {}", updateCount, now)
        }
    }

    private fun ValidatingEmailType.toMailType(): MailType =
        if (this == ValidatingEmailType.CHANGE_PASSWORD) MailType.CHANGE_PASSWORD
        else MailType.VALIDATE_EMAIL

    private fun ValidatingEmailType.toValidatingUrl(): String =
        if (this == ValidatingEmailType.CHANGE_PASSWORD) CHANGING_PASSWORD_EMAIL_URL else VALIDATING_EMAIL_URL
}
