package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.TravelLiking
import com.withaeng.domain.user.dto.UserTravelLikingDto

data class UserTravelLikingResponse(
    val travelLikings: Set<TravelLiking>? = emptySet(),
)

fun UserTravelLikingDto.toServiceResponse(): UserTravelLikingResponse =
    UserTravelLikingResponse(
        travelLikings = travelLikings,
    )