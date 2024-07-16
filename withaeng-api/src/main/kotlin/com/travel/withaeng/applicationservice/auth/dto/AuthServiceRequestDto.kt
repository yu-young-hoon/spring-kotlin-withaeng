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

data class ResendEmailServiceRequest(
    val email: String
)

data class ValidateEmailServiceRequest(
    val email: String,
    val code: String
)

data class SendEmailForChangePasswordServiceRequest(
    val email: String
)

data class ChangePasswordServiceRequest(
    val email: String,
    val code: String,
    val password: String
)
