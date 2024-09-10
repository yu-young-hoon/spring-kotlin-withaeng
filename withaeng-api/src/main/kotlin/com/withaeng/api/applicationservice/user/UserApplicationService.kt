package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.UpdateTravelPreferenceServiceRequest
import com.withaeng.api.applicationservice.user.dto.UserDetailsResponse
import com.withaeng.api.applicationservice.user.dto.toCommand
import com.withaeng.api.applicationservice.user.dto.toDetailResponse
import com.withaeng.domain.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserApplicationService(
    private val userService: UserService,
) {

    @Transactional
    fun updateTravelPreference(updateTravelPreferenceServiceRequest: UpdateTravelPreferenceServiceRequest): UserDetailsResponse {
        val userId = updateTravelPreferenceServiceRequest.userId
        val userDetailsDto =
            userService.updateTravelPreference(userId, updateTravelPreferenceServiceRequest.toCommand())
        return userDetailsDto.toDetailResponse()
    }
}
