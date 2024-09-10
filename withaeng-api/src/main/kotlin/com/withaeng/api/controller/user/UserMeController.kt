package com.withaeng.api.controller.user

import com.withaeng.api.applicationservice.user.UserApplicationService
import com.withaeng.api.applicationservice.user.dto.UserDetailServiceResponse
import com.withaeng.api.applicationservice.user.dto.UserSimpleServiceResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.controller.user.dto.UpdateProfileRequest
import com.withaeng.api.controller.user.dto.UpdateTravelPreferenceRequest
import com.withaeng.api.controller.user.dto.toServiceRequest
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User", description = "유저 정보 관리 API")
@RestController
@RequestMapping("/api/v1/user/me")
class UserMeController(private val userApplicationService: UserApplicationService) {

    @Operation(
        summary = "Update Profile",
        description = "프로필 업데이트 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/profile")
    fun updateProfile(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: UpdateProfileRequest,
    ): ApiResponse<UserSimpleServiceResponse> {
        return ApiResponse.success(
            userApplicationService.updateProfile(request.toServiceRequest(userInfo.id))
        )
    }

    @Operation(
        summary = "Update Travel Preference",
        description = "여행 선호 정보 업데이트 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/travel-preference")
    fun updateTravelPreference(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: UpdateTravelPreferenceRequest,
    ): ApiResponse<UserDetailServiceResponse> {
        return ApiResponse.success(
            userApplicationService.updateTravelPreference(request.toServiceRequest(userInfo.id))
        )
    }
}
