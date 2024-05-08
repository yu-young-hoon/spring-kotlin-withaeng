package com.travel.withaeng.controller.auth.dto

import com.travel.withaeng.applicationservice.auth.dto.SignInServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.SignUpServiceRequest
import com.travel.withaeng.applicationservice.auth.dto.ValidateEmailServiceRequest
import java.time.LocalDate

data class SignUpRequest(
    val isMale: Boolean,
    val birth: LocalDate,
    val email: String,
    val password: String
)

fun SignUpRequest.toServiceRequest(): SignUpServiceRequest = SignUpServiceRequest(
    isMale = isMale,
    birth = birth,
    email = email,
    password = password
)

data class SignInRequest(
    val email: String,
    val password: String
)

fun SignInRequest.toServiceRequest(): SignInServiceRequest = SignInServiceRequest(
    email = email,
    password = password
)

data class ValidateEmailRequest(
    val email: String,
    val code: String
)

fun ValidateEmailRequest.toServiceRequest(): ValidateEmailServiceRequest = ValidateEmailServiceRequest(
    email = email,
    code = code
)
