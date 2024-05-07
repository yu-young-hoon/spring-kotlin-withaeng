package com.travel.withaeng.domain.validateemail

data class ValidatingEmailDto(
    val id: Long,
    val email: String,
    val userId: Long,
    val code: String,
    val status: ValidatingEmailStatus
)

fun ValidatingEmail.toDto(): ValidatingEmailDto = ValidatingEmailDto(
    id = id,
    email = email,
    userId = userId,
    code = code,
    status = status
)