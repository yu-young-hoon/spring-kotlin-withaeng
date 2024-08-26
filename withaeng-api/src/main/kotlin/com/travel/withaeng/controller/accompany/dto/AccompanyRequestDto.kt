package com.travel.withaeng.controller.accompany.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.travel.withaeng.applicationservice.accompany.dto.CreateAccompanyServiceRequest
import com.travel.withaeng.applicationservice.accompany.dto.UpdateAccompanyServiceRequest
import com.travel.withaeng.domain.accompany.AccompanyAge
import com.travel.withaeng.domain.accompany.AccompanyAgeDeserializer
import com.travel.withaeng.domain.user.UserPreferAccompanyGender
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "[Request] 동행 게시글 생성")
data class CreateAccompanyRequest(
    @Schema(description = "동행 게시글 제목")
    val title: String,

    @Schema(description = "동행 게시글 내용")
    val content: String,

    @Schema(description = "동행 목적지의 대륙")
    val continent: String,

    @Schema(description = "동행 목적지의 나라")
    val country: String,

    @Schema(description = "동행 목적지의 도시")
    val city: String,

    @Schema(description = "동행 시작 날짜 (1999-01-01)")
    val startTripDate: LocalDate,

    @Schema(description = "동행 종료 날짜 (1999-01-01)")
    val endTripDate: LocalDate,

    @Schema(description = "동행 게시글 배너 이미지 URL")
    val bannerImageUrl: String? = null,

    @Schema(description = "동행 멤버수")
    val memberCount: Long,

    @Schema(description = "동행 게시글에 부착할 태그 아이디 리스트")
    val tagIds: Set<Long>? = emptySet(),

    @Schema(description = "동행 게시글에 게시된 오픈 카카오톡 URL")
    val openKakaoUrl: String,

    @Schema(description = "동행 시작 연령(누구나 가능의 경우 0)")
    @JsonDeserialize(using = AccompanyAgeDeserializer::class)
    val startAccompanyAge: AccompanyAge,

    @Schema(description = "동행 시작 연령(누구나 가능의 경우 99)")
    @JsonDeserialize(using = AccompanyAgeDeserializer::class)
    val endAccompanyAge: AccompanyAge,

    @Schema(description = "동행 선호 성별")
    val preferGender: UserPreferAccompanyGender,
)

@Schema(description = "[Request] 동행 게시글 수정")
fun CreateAccompanyRequest.toServiceRequest(
    userId: Long
): CreateAccompanyServiceRequest = CreateAccompanyServiceRequest(
    userId = userId,
    title = title,
    content = content,
    continent = continent,
    country = country,
    city = city,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    tagIds = tagIds,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
)

data class UpdateAccompanyRequest(
    @Schema(description = "동행 게시글 제목")
    val title: String? = null,

    @Schema(description = "동행 게시글 내용")
    val content: String? = null,

    @Schema(description = "동행 게시글 배너 이미지 URL")
    val bannerImageUrl: String? = null,

    @Schema(description = "동행 게시글에 부착할 태그 아이디 리스트")
    val tagIds: Set<Long>? = null,

    @Schema(description = "동행 게시글에 게시된 오픈 카카오톡 URL")
    val openKakaoUrl: String? = null,
)

fun UpdateAccompanyRequest.toServiceRequest(
    accompanyId: Long,
    userId: Long
): UpdateAccompanyServiceRequest = UpdateAccompanyServiceRequest(
    accompanyId = accompanyId,
    userId = userId,
    title = title,
    content = content,
    bannerImageUrl = bannerImageUrl,
    tagIds = tagIds,
    openKakaoUrl = openKakaoUrl,
)