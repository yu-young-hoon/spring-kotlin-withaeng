package com.withaeng.api.scheduler

import com.withaeng.domain.verificationemail.VerificationEmailDto
import com.withaeng.domain.verificationemail.VerificationEmailService
import com.withaeng.domain.verificationemail.VerificationEmailStatus
import com.withaeng.domain.verificationemail.VerificationEmailType
import com.withaeng.external.email.template.EmailTemplate
import com.withaeng.external.email.template.TemplatedEmailSender
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
    private val verificationEmailService: VerificationEmailService,
    private val templatedEmailSender: TemplatedEmailSender,
    @Value("\${withaeng.host}") private val host: String,
) {

    companion object {
        private const val FIXED_DELAY_SECONDS = 20L
        private const val VERIFY_EMAIL_REDIRECT_PATH = "/check-email"
        private const val CHANGE_PASSWORD_REDIRECT_PATH = "/check-email-pw"
    }

    private val log: Logger = LoggerFactory.getLogger(SendEmailScheduler::class.java)

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = FIXED_DELAY_SECONDS)
    @Async("asyncSchedulerExecutor")
    @Transactional
    fun sendEmail() {
        val now = LocalDateTime.now()
        log.info("Start sending emails at {}", now)

        val verificationEmails = getSendTargets()
        if (verificationEmails.isEmpty()) {
            log.info("No emails to send at {}", now)
            return
        }

        verificationEmails.forEach(this::sendVerificationEmail)

        val updatedCount = updateEmailStatuses(verificationEmails)
        log.info("End sending {} emails at {}", updatedCount, now)
    }

    private fun getSendTargets() = verificationEmailService.findAllByStatusNot(VerificationEmailStatus.DONE)

    private fun sendVerificationEmail(verificationEmail: VerificationEmailDto) =
        templatedEmailSender.send(
            to = verificationEmail.email,
            template = verificationEmail.toEmailTemplate(),
            variables = mapOf(
                "email" to verificationEmail.email,
                "redirectUrl" to createRedirectUrl(verificationEmail),
            ),
        )

    private fun updateEmailStatuses(verificationEmails: List<VerificationEmailDto>): Int {
        val emailIds = verificationEmails.map { it.id }.toSet()
        return verificationEmailService.updateStatusByIds(emailIds, VerificationEmailStatus.DONE)
    }

    private fun createRedirectUrl(verificationEmail: VerificationEmailDto): String {
        val verifyHost = verificationEmail.host?: host
        return "${verifyHost}${verificationEmail.toRedirectPath()}?email=${verificationEmail.email}&code=${verificationEmail.code}"
    }

    private fun VerificationEmailDto.toEmailTemplate(): EmailTemplate = when (this.type) {
        VerificationEmailType.VERIFY_EMAIL -> EmailTemplate.VERIFY_EMAIL
        VerificationEmailType.CHANGE_PASSWORD -> EmailTemplate.CHANGE_PASSWORD
    }

    private fun VerificationEmailDto.toRedirectPath(): String = when (this.type) {
        VerificationEmailType.VERIFY_EMAIL -> VERIFY_EMAIL_REDIRECT_PATH
        VerificationEmailType.CHANGE_PASSWORD -> CHANGE_PASSWORD_REDIRECT_PATH
    }
}

