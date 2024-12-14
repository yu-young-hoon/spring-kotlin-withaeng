package com.withaeng.api.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.withaeng.api.common.ApiErrorResponse
import com.withaeng.common.exception.WithaengExceptionType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler

class HttpStatusAccessDeniedHandler : AccessDeniedHandler {
    private val log: Logger = LoggerFactory.getLogger(HttpStatusAccessDeniedHandler::class.java)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: org.springframework.security.access.AccessDeniedException,
    ) {
        log.debug("Access Denied: ${accessDeniedException.message}")

        val error = WithaengExceptionType.VERIFY_NOT_YET_ERROR
        val responseBody: String = ObjectMapper().writeValueAsString(ApiErrorResponse(error.name, error.message))

        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.writer.write(responseBody)
    }
}