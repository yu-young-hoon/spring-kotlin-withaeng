package com.withaeng.domain.verificationemail

import java.time.LocalDateTime

data class VerificationEmailDto(
    val id: Long,
    val createdAt: LocalDateTime,
    val email: String,
    val code: String,
    val type: VerificationEmailType,
    val status: VerificationEmailStatus,
    val userId: Long,
)

fun VerificationEmail.toDto(): VerificationEmailDto = VerificationEmailDto(
    id = id,
    createdAt = createdAt,
    email = email,
    code = code,
    type = type,
    status = status,
    userId = userId,
)
