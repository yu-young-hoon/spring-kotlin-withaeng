package com.withaeng.api.applicationservice.auth.dto

import com.withaeng.domain.user.Gender
import com.withaeng.domain.user.dto.CreateUserCommand
import java.time.LocalDate

data class SignUpServiceRequest(
    val email: String,
    val password: String,
    val birth: LocalDate,
    val gender: Gender,
    val host: String?,
)

fun SignInForOAuthServiceRequest.toCommand(
    temporaryNickname: String,
    name: String,
    email: String,
    googleId: String,
    birth: LocalDate?,
    gender: Gender?,
): CreateUserCommand =
    CreateUserCommand(
        nickname = temporaryNickname,
        name = name,
        email = email,
        googleId = googleId,
        birth = birth,
        gender = gender,
    )

data class SignInServiceRequest(
    val email: String,
    val password: String,
)

data class SignInForOAuthServiceRequest(
    val code: String,
)

data class ResendEmailServiceRequest(
    val email: String,
    val host: String?,
)

data class VerifyEmailServiceRequest(
    val email: String,
    val code: String,
)

data class SendEmailForChangePasswordServiceRequest(
    val email: String,
    val host: String?,
)

data class ChangePasswordServiceRequest(
    val email: String,
    val code: String,
    val password: String,
)
