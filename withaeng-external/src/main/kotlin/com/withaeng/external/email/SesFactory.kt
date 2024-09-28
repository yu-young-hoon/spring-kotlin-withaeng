package com.withaeng.external.email

import software.amazon.awssdk.services.ses.model.*
import java.nio.charset.StandardCharsets

object SesFactory {

    fun createRequest(to: String, from: String, subject: String, content: String): SendEmailRequest =
        SendEmailRequest.builder()
            .destination(destination(to))
            .source(from)
            .message(message(utf8Content(subject), body(content)))
            .build()

    private fun destination(to: String): Destination =
        Destination.builder()
            .toAddresses(to)
            .build()

    private fun message(content: Content, body: Body): Message =
        Message.builder()
            .subject(content)
            .body(body)
            .build()

    private fun body(content: String): Body =
        Body.builder()
            .html(utf8Content(content))
            .build()

    private fun utf8Content(subject: String): Content =
        Content.builder()
            .charset(StandardCharsets.UTF_8.name())
            .data(subject)
            .build()
}