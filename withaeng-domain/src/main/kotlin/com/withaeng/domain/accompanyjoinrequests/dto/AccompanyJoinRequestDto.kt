package com.withaeng.domain.accompanyjoinrequests.dto

import com.querydsl.core.annotations.QueryProjection
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequest
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequestStatus
import com.withaeng.domain.user.Gender
import java.time.LocalDate
import java.time.LocalDateTime

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

data class FindAccompanyJoinRequestDto @QueryProjection constructor(
    val joinRequestId: Long,
    val joinUserId: Long,
    val joinRequestStatus: AccompanyJoinRequestStatus,
    val nickname: String,
    val profileImageUrl: String?,
    val gender: Gender,
    val introduction: String?,
    val joinDate: LocalDateTime,
    val birth: LocalDate?,
    val mannerScore: Double,
    // TODO : 여행 관심사 Set 추가 필요
    // TODO : 온도 추가 필요
    // TODO : 연령대 추가 필요
)