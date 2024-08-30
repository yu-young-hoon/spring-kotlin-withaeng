package com.withaeng.api.common

object WhiteList {

    fun getWhiteListForSecurityConfig(): List<String> = listOf(
        // swagger
        "/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**",
        // error page
        "/error/**",
        // Auth endpoints
        "/api/v1/auth/**",
        // Test endpoints
        "/api/v1/test/**"
    )
}