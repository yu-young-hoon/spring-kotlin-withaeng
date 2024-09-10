package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.*
import com.withaeng.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val userService: UserService,
) {

    fun updateProfile(serviceRequest: UpdateProfileServiceRequest): UserSimpleServiceResponse {
        return userService.updateProfile(serviceRequest.userId, serviceRequest.toCommand()).toSimpleResponse()
    }

    fun updateTravelPreference(serviceRequest: UpdateTravelPreferenceServiceRequest): UserDetailServiceResponse {
        return userService.updateTravelPreference(serviceRequest.userId, serviceRequest.toCommand()).toDetailResponse()
    }
}
