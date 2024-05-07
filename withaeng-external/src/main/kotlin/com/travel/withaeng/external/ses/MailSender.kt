package com.travel.withaeng.external.ses

interface MailSender {

    fun sendValidatingEmail(validatingEmailUrl: String, vararg to: String)
}