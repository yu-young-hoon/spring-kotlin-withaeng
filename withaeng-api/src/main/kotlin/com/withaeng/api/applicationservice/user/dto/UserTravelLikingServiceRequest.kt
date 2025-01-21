package com.withaeng.api.applicationservice.user.dto

import com.withaeng.domain.user.TravelLiking
import com.withaeng.domain.user.dto.UpdateTravelLikingCommand

data class UpdateTravelLikingServiceRequest(
    val travelLikings: Set<TravelLiking> = emptySet(),
)

fun UpdateTravelLikingServiceRequest.toCommand(): UpdateTravelLikingCommand =
    UpdateTravelLikingCommand(
        travelLikings = travelLikings,
    )