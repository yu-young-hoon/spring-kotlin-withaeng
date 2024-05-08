package com.travel.withaeng.applicationservice.auth.dto

data class UserResponse(
    val userId: Long,
    val email: String,
    val accessToken: String
)