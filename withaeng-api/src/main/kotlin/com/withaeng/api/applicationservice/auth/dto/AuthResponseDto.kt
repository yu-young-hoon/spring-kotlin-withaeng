package com.withaeng.api.applicationservice.auth.dto

data class UserResponse(
    val userId: Long,
    val email: String?,
    val accessToken: String,
    val signUp: Boolean = false,
)