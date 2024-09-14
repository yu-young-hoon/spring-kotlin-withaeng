package com.withaeng.api.scheduler

import com.withaeng.domain.validateemail.ValidatingEmailService
import com.withaeng.domain.validateemail.ValidatingEmailStatus
import com.withaeng.domain.validateemail.ValidatingEmailType
import com.withaeng.external.ses.MailSender
import com.withaeng.external.ses.MailType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Component
class SendEmailScheduler(
    private val validatingEmailService: ValidatingEmailService,
    private val mailSender: MailSender,
    @Value("\${withaeng.host}")
    private val host: String,
) {

    companion object {
        private const val EMAIL_VALIDATE_REDIRECT_PATH = "/check-email"
        private const val CHANGE_PASSWORD_REDIRECT_PATH = "/check-email-pw"
    }

    private val log: Logger = LoggerFactory.getLogger(SendEmailScheduler::class.java)

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 20)
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
        if (this == ValidatingEmailType.VALIDATE_EMAIL)
            MailType.VALIDATE_EMAIL else MailType.CHANGE_PASSWORD

    private fun ValidatingEmailType.toValidatingUrl(): String =
        if (this == ValidatingEmailType.VALIDATE_EMAIL)
            host + EMAIL_VALIDATE_REDIRECT_PATH else host + CHANGE_PASSWORD_REDIRECT_PATH
}
