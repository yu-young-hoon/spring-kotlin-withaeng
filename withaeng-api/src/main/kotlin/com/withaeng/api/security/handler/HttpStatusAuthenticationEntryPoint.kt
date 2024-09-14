package com.withaeng.api.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.withaeng.api.common.ApiResponse
import com.withaeng.common.exception.WithaengExceptionType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class HttpStatusAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val log: Logger = LoggerFactory.getLogger(HttpStatusAuthenticationEntryPoint::class.java)
    private val objectMapper = ObjectMapper()

    companion object {
        private const val ATTRIBUTE = "token_exception_message"
    }

    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        log.debug("Not Authenticated: ${authException.message}")
        val attribute = request.getAttribute(ATTRIBUTE) as String

        responseBuilder(response, attribute)
    }

    private fun responseBuilder(response: HttpServletResponse, exceptionMessage: String) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.print(
            objectMapper.writeValueAsString(
                ApiResponse.fail(WithaengExceptionType.AUTHENTICATION_FAILURE, exceptionMessage)
            )
        )
    }
}