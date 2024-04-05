package com.travel.withaeng.external.ses

interface MailSender {
    fun send(subject: String, variables: Map<String, Any>, vararg to: String)
}