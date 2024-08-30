package com.withaeng.domain.validateemail

import java.time.LocalDateTime

data class ValidatingEmailDto(
    val id: Long,
    val email: String,
    val userId: Long,
    val code: String,
    val status: ValidatingEmailStatus,
    val type: ValidatingEmailType,
    val createdAt: LocalDateTime
)

fun ValidatingEmail.toDto(): ValidatingEmailDto = ValidatingEmailDto(
    id = id,
    email = email,
    userId = userId,
    code = code,
    status = status,
    type = type,
    createdAt = createdAt
)
