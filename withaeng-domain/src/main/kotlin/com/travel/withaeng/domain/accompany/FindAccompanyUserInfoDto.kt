package com.travel.withaeng.domain.accompany

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class FindAccompanyUserInfoDto @QueryProjection constructor(
    val nickname: String,
    val profileImageUrl: String?,
    val isMale: Boolean,
    val bio: String?,
    val joinDate: LocalDateTime,
    // TODO : 여행 관심사 Set 추가 필요
    // TODO : 온도 추가 필요
    // TODO : 연령대 추가 필요
)