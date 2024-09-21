package com.withaeng.external.email

interface EmailSender {

    fun send(to: String, subject: String, content: String)
}