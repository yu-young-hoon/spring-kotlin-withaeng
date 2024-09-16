package com.withaeng.api.applicationservice.accompany.dto

import com.withaeng.domain.accompany.AccompanyDestination
import com.withaeng.domain.accompany.AccompanyPreferGender
import com.withaeng.domain.accompany.dto.FindAccompanyDto
import com.withaeng.domain.accompany.dto.FindAccompanyUserInfoDto
import java.time.LocalDate

data class FindAccompanyResponse(
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
    val preferGender: AccompanyPreferGender,
    val tags: Set<String>? = emptySet(),
    val likeCount: Long = 0,
    val author: FindAccompanyUserInfoDto,
    val approvalPendingUsers: Set<FindAccompanyUserInfoDto>? = null,
)

fun FindAccompanyDto.toAccompanyResponse() = FindAccompanyResponse(
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
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
    tags = tags,
    author = author,
)