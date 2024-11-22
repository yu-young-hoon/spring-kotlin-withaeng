package com.withaeng.api.controller.auth.dto

import com.withaeng.api.applicationservice.auth.dto.*
import com.withaeng.domain.user.Gender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "[Request] 회원가입")
data class SignUpRequest(
    @Schema(description = "회원가입 할 이메일")
    val email: String,

    @Schema(description = "회원가입 할 패스워드")
    val password: String,

    @Schema(description = "생년월일 format:[2024-05-09]")
    val birth: LocalDate,

    @Schema(description = "성별")
    val gender: Gender = Gender.MALE,
)

fun SignUpRequest.toServiceRequest(): SignUpServiceRequest = SignUpServiceRequest(
    email = email,
    password = password,
    birth = birth,
    gender = gender,
)

@Schema(description = "[Request] 로그인")
data class SignInRequest(
    @Schema(description = "로그인 할 이메일")
    val email: String,

    @Schema(description = "로그인 할 패스워드")
    val password: String,
)

fun SignInRequest.toServiceRequest(): SignInServiceRequest = SignInServiceRequest(
    email = email,
    password = password
)

@Schema(description = "[Request] 이메일 재전송")
data class ResendEmailRequest(
    @Schema(description = "재전송 할 이메일")
    val email: String,
)

fun ResendEmailRequest.toServiceRequest(): ResendEmailServiceRequest = ResendEmailServiceRequest(
    email = email
)

@Schema(description = "[Request] 이메일 인증")
data class VerifyEmailRequest(
    @Schema(description = "이메일 인증 할 이메일")
    val email: String,

    @Schema(description = "이메일 인증으로 보낸 코드 (UUID 형태)")
    val code: String,
)

fun VerifyEmailRequest.toServiceRequest(): VerifyEmailServiceRequest = VerifyEmailServiceRequest(
    email = email,
    code = code
)


@Schema(description = "[Request] 비밀번호 재설정을 위한 이메일 전송")
data class SendEmailForChangePasswordRequest(
    @Schema(description = "이메일 인증 할 이메일")
    val email: String,
)

fun SendEmailForChangePasswordRequest.toServiceRequest(): SendEmailForChangePasswordServiceRequest =
    SendEmailForChangePasswordServiceRequest(email)

@Schema(description = "[Request] 비밀번호 재설정")
data class ChangePasswordRequest(
    @Schema(description = "이메일 인증 한 이메일")
    val email: String,

    @Schema(description = "이메일 인증으로 받은 코드 (UUID 형태)")
    val code: String,

    @Schema(description = "새로운 패스워드")
    val password: String,
)

fun ChangePasswordRequest.toServiceRequest(): ChangePasswordServiceRequest = ChangePasswordServiceRequest(
    email = email,
    code = code,
    password = password
)
