package com.withaeng.api.controller.user.dto

import com.withaeng.api.applicationservice.user.dto.UpdateTravelLikingServiceRequest
import com.withaeng.domain.user.TravelLiking
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "[Request] User 여행 선호 스타일 정보를 추가합니다")
data class PutTravelLikingRequest(
    @Schema(description = "유저 MBTI")
    val travelLikings: Set<TravelLiking> = emptySet(),
)

fun PutTravelLikingRequest.toServiceRequest(): UpdateTravelLikingServiceRequest =
    UpdateTravelLikingServiceRequest(
        travelLikings = travelLikings,
    )