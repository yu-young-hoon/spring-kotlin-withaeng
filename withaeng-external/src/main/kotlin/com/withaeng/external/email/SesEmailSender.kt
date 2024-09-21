package com.withaeng.external.email

import org.springframework.stereotype.Component
import software.amazon.awssdk.services.ses.SesClient

@Component
class SesEmailSender(
    private val sesClient: SesClient,
) : EmailSender {

    companion object {
        private const val FROM = "withaeng@gmail.com"
    }

    override fun send(to: String, subject: String, content: String) {
        val request = SesFactory.createRequest(
            to = to,
            from = FROM,
            subject = subject,
            content = content
        )
        sesClient.sendEmail(request)
    }
}
