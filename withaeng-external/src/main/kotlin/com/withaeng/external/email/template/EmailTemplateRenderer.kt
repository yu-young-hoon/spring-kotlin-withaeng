package com.withaeng.external.email.template

import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Component
class EmailTemplateRenderer(
    private val templateEngine: TemplateEngine,
) {
    fun render(template: EmailTemplate, variables: Map<String, Any>): String {
        template.validateVariables(variables)
        val context = Context().apply {
            setVariables(variables)
        }
        return templateEngine.process(template.templateName(), context)
    }
}