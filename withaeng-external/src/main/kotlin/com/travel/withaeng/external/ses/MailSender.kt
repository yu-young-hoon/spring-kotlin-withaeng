package com.travel.withaeng.external.ses

interface MailSender {

    fun send(redirectUrl: String, to: String, type: MailType)
}
