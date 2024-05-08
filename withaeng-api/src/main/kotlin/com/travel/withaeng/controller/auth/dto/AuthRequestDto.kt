package com.travel.withaeng.controller.auth.dto

import com.travel.withaeng.applicationservice.auth.dto.SignUpServiceRequest
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