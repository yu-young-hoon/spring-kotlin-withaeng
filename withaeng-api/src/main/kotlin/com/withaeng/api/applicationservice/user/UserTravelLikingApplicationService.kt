package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.UpdateTravelLikingServiceRequest
import com.withaeng.api.applicationservice.user.dto.UserTravelLikingResponse
import com.withaeng.api.applicationservice.user.dto.toCommand
import com.withaeng.api.applicationservice.user.dto.toServiceResponse
import com.withaeng.api.common.IdResponse
import com.withaeng.domain.user.UserService
import com.withaeng.domain.user.dto.UserSimpleDto
import org.springframework.stereotype.Service

@Service
class UserTravelLikingApplicationService(
    private val userService: UserService,
) {
    fun getTravelLiking(userId: Long): UserTravelLikingResponse {
        return userService.findDetailById(userId).travelLiking?.toServiceResponse()
            ?: UserTravelLikingResponse()
    }

    fun updateTravelLiking(userId: Long, serviceRequest: UpdateTravelLikingServiceRequest): IdResponse {
        return userService.replaceTravelLiking(userId, serviceRequest.toCommand()).toIdResponse()
    }

    fun UserSimpleDto.toIdResponse(): IdResponse = IdResponse(id)
}