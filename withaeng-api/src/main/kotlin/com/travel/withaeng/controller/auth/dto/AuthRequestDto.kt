package com.travel.withaeng.controller.auth.dto

import com.travel.withaeng.applicationservice.auth.dto.SignInServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.SignUpServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.ValidateEmailServiceRequest
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "[Request] 회원가입")
data class SignUpRequest(
    @Schema(description = "남/여 성별")
    val isMale: Boolean,

    @Schema(description = "생년월일 format:[2024-05-09]")
    val birth: LocalDate,

    @Schema(description = "회원가입 할 이메일")
    val email: String,

    @Schema(description = "회원가입 할 패스워드")
    val password: String
)

fun SignUpRequest.toServiceRequest(): SignUpServiceRequest = SignUpServiceRequest(
    isMale = isMale,
    birth = birth,
    email = email,
    password = password
)

@Schema(description = "[Request] 로그인")
data class SignInRequest(
    @Schema(description = "로그인 할 이메일")
    val email: String,

    @Schema(description = "로그인 할 패스워드")
    val password: String
)

fun SignInRequest.toServiceRequest(): SignInServiceRequest = SignInServiceRequest(
    email = email,
    password = password
)

@Schema(description = "[Request] 이메일 인증")
data class ValidateEmailRequest(
    @Schema(description = "이메일 인증 할 이메일")
    val email: String,

    @Schema(description = "이메일 인증으로 보낸 코드 (UUID 형태)")
    val code: String
)

fun ValidateEmailRequest.toServiceRequest(): ValidateEmailServiceRequest = ValidateEmailServiceRequest(
    email = email,
    code = code
)
