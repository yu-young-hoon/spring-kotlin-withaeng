package com.withaeng.api.common

object WhiteList {

    fun getWhiteListForSecurityConfig(): List<String> = listOf(
        // swagger
        "/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**",
        // H2 console
        "/h2-console/**",
    )
}