package com.travel.withaeng.domain.accompany

import com.querydsl.core.annotations.QueryProjection
import com.travel.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class FindAccompanyDto @QueryProjection constructor(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val viewCount: Long,
    val openKakaoUrl: String,
    val startAccompanyAge: Int,
    val endAccompanyAge: Int,
    val preferGender: UserPreferAccompanyGender,
    val tagIds: Set<Long>? = emptySet(),
    val likeCount: Long = 0,
    val author: FindAccompanyUserInfoDto,
)