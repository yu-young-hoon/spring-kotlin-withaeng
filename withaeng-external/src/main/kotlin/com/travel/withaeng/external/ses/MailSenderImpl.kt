package com.travel.withaeng.external.ses

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.nio.charset.StandardCharsets

@Service
class MailSenderImpl(private val emailService: AmazonSimpleEmailService, private val templateEngine: TemplateEngine) :
    MailSender {

    @Value("\${cloud.aws.ses.from}")
    private lateinit var from: String

    override fun send(subject: String, variables: Map<String, Any>, vararg to: String) {
        val content = templateEngine.process("email-template", createContext(variables))
        val request = createSendEmailRequest(subject, content, *to)
        emailService.sendEmail(request)
    }

    private fun createContext(variables: Map<String, Any>): Context {
        return Context().apply { setVariables(variables) }
    }

    private fun createSendEmailRequest(subject: String, content: String, vararg to: String): SendEmailRequest {
        return SendEmailRequest()
            .withDestination(Destination().withToAddresses(*to))
            .withSource(from)
            .withMessage(
                Message()
                    .withSubject(Content().withCharset(StandardCharsets.UTF_8.name()).withData(subject))
                    .withBody(Body().withHtml(Content().withCharset(StandardCharsets.UTF_8.name()).withData(content)))
            )
    }
}