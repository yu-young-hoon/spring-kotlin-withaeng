package com.travel.withaeng.domain.validateemail

import java.time.LocalDateTime

data class ValidatingEmailDto(
    val id: Long,
    val email: String,
    val userId: Long,
    val code: String,
    val status: ValidatingEmailStatus,
    val createdAt: LocalDateTime
)

fun ValidatingEmail.toDto(): ValidatingEmailDto = ValidatingEmailDto(
    id = id,
    email = email,
    userId = userId,
    code = code,
    status = status,
    createdAt = createdAt
)
