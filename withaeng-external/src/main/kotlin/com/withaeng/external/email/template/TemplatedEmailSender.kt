package com.withaeng.external.email.template

interface TemplatedEmailSender {
    fun send(to: String, template: EmailTemplate, variables: Map<String, Any>)
}