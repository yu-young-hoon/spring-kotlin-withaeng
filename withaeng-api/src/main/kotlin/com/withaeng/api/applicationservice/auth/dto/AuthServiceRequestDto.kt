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

fun SignUpServiceRequest.toCommand(temporaryNickname: String, encodedPassword: String): CreateUserCommand =
    CreateUserCommand(
        email = email,
        password = encodedPassword,
        birth = birth,
        gender = gender,
        nickname = temporaryNickname,
    )

fun SignInForOAuthServiceRequest.toCommand(temporaryNickname: String, email: String, googleId: String, birth: LocalDate?, gender: Gender?): CreateUserCommand =
    CreateUserCommand(
        nickname = temporaryNickname,
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
    val host: String?
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
