package com.withaeng.domain.accompany.dto

import com.querydsl.core.annotations.QueryProjection
import com.withaeng.domain.accompany.Accompany
import com.withaeng.domain.accompany.AccompanyAge
import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.AccompanyStatus
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyDto(
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val tags: Set<String>? = emptySet(),
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

data class UpdateAccompanyDto(
    val accompanyId: Long,
    val content: String? = null,
    val tags: Set<String>? = null,
)

data class AccompanyDto(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val accompanyStatus: AccompanyStatus,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val viewCount: Long,
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
    val tags: Set<String>? = emptySet(),
)

fun Accompany.toDto(): AccompanyDto = AccompanyDto(
    id = id,
    userId = userId,
    title = title,
    content = content,
    accompanyStatus = accompanyStatus,
    destination = accompanyDestination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    viewCount = accompanyStatistics?.viewCount ?: 0,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = AccompanyAge.fromValue(startAccompanyAge),
    endAccompanyAge = AccompanyAge.fromValue(endAccompanyAge),
    preferGender = preferGender,
    tags = tags,
)

data class SearchAccompanyDto @QueryProjection constructor(
    val id: Long,
    val bannerImageUrl: String?,
    val status: AccompanyStatus,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currentMemberCount: Long,
    val maxMemberCount: Long,
    val title: String,
    val tags: Set<String>? = emptySet(),
    val host: SearchAccompanyHostDto,
)

data class SearchAccompanyHostDto @QueryProjection constructor(
    val id: Long,
    val nickname: String,
    val profileImageUrl: String?,
)
