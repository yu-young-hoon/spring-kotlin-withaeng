package com.travel.withaeng.external.ses

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.nio.charset.StandardCharsets

@Service
class MailSenderImpl(
    private val emailService: AmazonSimpleEmailService,
    private val templateEngine: TemplateEngine
) : MailSender {

    @Value("\${cloud.aws.ses.from}")
    private lateinit var from: String

    override fun sendValidatingEmail(validatingEmailUrl: String, to: String) {
        send(
            template = "email-template",
            subject = VALIDATING_EMAIL_SUBJECT,
            variables = mapOf(
                VARIABLE_THYMELEAF_VALIDATING_EMAIL_URL to validatingEmailUrl,
                VARIABLE_THYMELEAF_EMAIL to to
            ),
            to = to
        )
    }

    private fun send(template: String, subject: String, variables: Map<String, Any>, to: String) {
        val content = templateEngine.process(template, createContext(variables))
        val request = createSendEmailRequest(subject, content, to)
        emailService.sendEmail(request)
    }

    private fun createContext(variables: Map<String, Any>): Context {
        return Context().apply { setVariables(variables) }
    }

    private fun createSendEmailRequest(subject: String, content: String, to: String): SendEmailRequest {
        return SendEmailRequest()
            .withDestination(Destination().withToAddresses(to))
            .withSource(from)
            .withMessage(
                Message()
                    .withSubject(Content().withCharset(StandardCharsets.UTF_8.name()).withData(subject))
                    .withBody(Body().withHtml(Content().withCharset(StandardCharsets.UTF_8.name()).withData(content)))
            )
    }

    companion object {
        private const val VALIDATING_EMAIL_SUBJECT = "같이행 서비스 이메일 인증을 부탁드립니다."

        private const val VARIABLE_THYMELEAF_VALIDATING_EMAIL_URL = "validatingEmailUrl"
        private const val VARIABLE_THYMELEAF_EMAIL = "email"
    }
}
