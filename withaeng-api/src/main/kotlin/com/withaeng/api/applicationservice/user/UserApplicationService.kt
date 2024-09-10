package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.*
import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val userService: UserService,
    private val accompanyService: AccompanyService,
) {

    fun getProfile(userId: Long): UserStatisticalProfileResponse {
        val userDetail = userService.findDetailById(userId)
        val profileCompletionPercentage = userService.getProfileCompletionPercentage(userId)
        val accompanyCount = accompanyService.countByUserId(userId)
        return UserStatisticalProfileResponse.of(
            userDetail = userDetail,
            profileCompletionPercentage = profileCompletionPercentage,
            accompanyCount = accompanyCount,
        )
    }

    fun getTravelPreference(userId: Long): UserTravelPreferenceResponse {
        return userService.findDetailById(userId).travelPreference?.toServiceResponse()
            ?: UserTravelPreferenceResponse()
    }

    fun updateProfile(serviceRequest: UpdateProfileServiceRequest): UserSimpleResponse {
        return userService.updateProfile(serviceRequest.userId, serviceRequest.toCommand()).toSimpleResponse()
    }

    fun updateTravelPreference(serviceRequest: UpdateTravelPreferenceServiceRequest): UserDetailResponse {
        return userService.updateTravelPreference(serviceRequest.userId, serviceRequest.toCommand()).toDetailResponse()
    }
}
