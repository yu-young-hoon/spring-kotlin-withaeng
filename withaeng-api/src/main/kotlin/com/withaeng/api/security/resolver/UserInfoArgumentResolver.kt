package com.withaeng.api.security.resolver

import com.withaeng.api.security.authentication.JwtAuthentication
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserInfoArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(GetAuth::class.java)
                && parameter.parameter.type == UserInfo::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return when (val authentication = SecurityContextHolder.getContext().authentication) {
            null -> null
            is JwtAuthentication -> authentication.principal
            else -> throw WithaengException.of(
                type = WithaengExceptionType.AUTH_ERROR,
                message = "The argument of GetAuth annotation is not of type UserInfo class."
            )
        }
    }
}