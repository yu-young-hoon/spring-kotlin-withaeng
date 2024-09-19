package com.withaeng.domain.accompanyjoinrequests.dto

import com.withaeng.domain.accompanyrequests.AccompanyJoinRequest
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequestStatus

data class AccompanyJoinRequestDto(
    val id: Long,
    val userId: Long,
    val accompanyJoinRequestStatus: AccompanyJoinRequestStatus,
    val accompanyId: Long,
)

fun AccompanyJoinRequest.toDto() = AccompanyJoinRequestDto(
    id = id,
    userId = userId,
    accompanyJoinRequestStatus = status,
    accompanyId = accompany.id,
)