package com.withaeng.api.applicationservice.auth.dto

import com.withaeng.domain.user.Gender
import com.withaeng.domain.user.dto.CreateUserCommand
import java.time.LocalDate

data class SignUpServiceRequest(
    val email: String,
    val password: String,
    val birth: LocalDate,
    val gender: Gender,
)

fun SignUpServiceRequest.toCommand(temporaryNickname: String, encodedPassword: String): CreateUserCommand =
    CreateUserCommand(
        email = email,
        password = encodedPassword,
        birth = birth,
        gender = gender,
        nickname = temporaryNickname,
    )

data class SignInServiceRequest(
    val email: String,
    val password: String,
)

data class ResendEmailServiceRequest(
    val email: String,
)

data class VerifyEmailServiceRequest(
    val email: String,
    val code: String,
)

data class SendEmailForChangePasswordServiceRequest(
    val email: String,
)

data class ChangePasswordServiceRequest(
    val email: String,
    val code: String,
    val password: String,
)
