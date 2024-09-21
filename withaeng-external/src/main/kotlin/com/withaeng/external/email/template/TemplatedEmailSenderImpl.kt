package com.withaeng.external.email.template

import com.withaeng.external.email.EmailSender
import org.springframework.stereotype.Component

@Component
class TemplatedEmailSenderImpl(
    private val emailSender: EmailSender,
    private val templateRenderer: EmailTemplateRenderer,
) : TemplatedEmailSender {

    override fun send(to: String, template: EmailTemplate, variables: Map<String, Any>) {
        val content = templateRenderer.render(template, variables)
        emailSender.send(to, template.subject(), content)
    }
}
