package com.withaeng.external.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.ses.SesClient

@Component
class SesEmailSender(
    private val sesClient: SesClient,
    @Value("\${cloud.aws.ses.from}")
    private var from: String,
) : EmailSender {

    override fun send(to: String, subject: String, content: String) {
        val request = SesFactory.createRequest(to, subject, from, content)
        sesClient.sendEmail(request)
    }
}
