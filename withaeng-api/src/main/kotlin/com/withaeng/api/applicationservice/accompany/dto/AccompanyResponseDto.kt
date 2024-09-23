package com.withaeng.api.applicationservice.accompany.dto

import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.AccompanyPreferGender
import com.withaeng.domain.accompany.AccompanyStatus
import com.withaeng.domain.accompany.dto.AccompanyDto
import com.withaeng.domain.accompany.dto.SearchAccompanyDto
import java.time.LocalDate

data class AccompanyResponse(
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
    val likeCount: Long,
    val tags: Set<String>? = null,
    val openKakaoUrl: String? = null,
    val startAccompanyAge: Int,
    val endAccompanyAge: Int,
    val preferGender: AccompanyPreferGender,
)

fun AccompanyDto.toAccompanyResponse(likeCount: Long): AccompanyResponse = AccompanyResponse(
    id = id,
    userId = userId,
    title = title,
    content = content,
    destination = destination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    viewCount = viewCount,
    likeCount = likeCount,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge.value,
    endAccompanyAge = endAccompanyAge.value,
    preferGender = preferGender,
    tags = tags,
)

data class AccompanySummaryResponse(
    val id: Long,
    val bannerImageUrl: String?,
    val status: AccompanyStatus,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currentMemberCount: Long,
    val maxMemberCount: Long,
    val title: String,
    val tags: Set<String>? = null,
    val host: AccompanyHostSummaryResponse,
)

data class AccompanyHostSummaryResponse(
    val id: Long,
    val profileImageUrl: String?,
    val nickname: String,
)

fun SearchAccompanyDto.toAccompanyResponse(): AccompanySummaryResponse = AccompanySummaryResponse(
    id = id,
    bannerImageUrl = bannerImageUrl,
    status = status,
    startDate = startDate,
    endDate = endDate,
    currentMemberCount = currentMemberCount,
    maxMemberCount = maxMemberCount,
    title = title,
    tags = tags,
    host = AccompanyHostSummaryResponse(
        id = host.id,
        nickname = host.nickname,
        profileImageUrl = host.profileImageUrl,
    ),
)

data class CreateAccompanyResponse(
    val id: Long,
    val preSignedUrl: String? = null,
)