package com.travel.withaeng.common

object WhiteList {

    fun getWhiteListForSecurityConfig(): List<String> = listOf(
        // swagger
        "/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**",
        // error page
        "/error/**",
        // Auth endpoints
        "/api/v1/auth/**",
    )
}