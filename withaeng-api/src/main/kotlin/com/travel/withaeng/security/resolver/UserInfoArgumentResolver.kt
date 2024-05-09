package com.travel.withaeng.security.resolver

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.security.authentication.JwtAuthentication
import com.travel.withaeng.security.authentication.UserInfo
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
    ): Any {
        return when (val authentication = SecurityContextHolder.getContext().authentication) {
            null -> throw WithaengException.of(WithaengExceptionType.AUTH_ERROR)
            is JwtAuthentication -> authentication.principal
            else -> throw WithaengException.of(
                type = WithaengExceptionType.AUTH_ERROR,
                message = "The argument of GetAuth annotation is not of type UserInfo class."
            )
        }
    }
}