package com.travel.withaeng.applicationservice.auth.dto

import java.time.LocalDate

data class SignUpServiceRequest(
    val isMale: Boolean,
    val birth: LocalDate,
    val email: String,
    val password: String
)

data class SignInServiceRequest(
    val email: String,
    val password: String
)