package com.withaeng.api.controller.user

import com.withaeng.api.applicationservice.user.UserApplicationService
import com.withaeng.api.applicationservice.user.dto.UpdateProfileResponse
import com.withaeng.api.applicationservice.user.dto.UserDetailResponse
import com.withaeng.api.applicationservice.user.dto.UserStatisticalProfileResponse
import com.withaeng.api.applicationservice.user.dto.UserTravelPreferenceResponse
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
import org.springframework.web.bind.annotation.*

@Tag(name = "User", description = "유저 정보 관리 API")
@RestController
@RequestMapping("/api/v1/user/me")
class UserMeController(private val userApplicationService: UserApplicationService) {

    @Operation(
        summary = "Get Statistics",
        description = "유저 통계 정보 조회 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @GetMapping("/profile")
    fun getProfile(
        @GetAuth userInfo: UserInfo,
    ): ApiResponse<UserStatisticalProfileResponse> {
        return ApiResponse.success(
            userApplicationService.getProfile(userInfo.id)
        )
    }

    @Operation(
        summary = "Get User Detail",
        description = "유저 상세 정보 조회 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @GetMapping("/travel-preference")
    fun getTravelPreference(
        @GetAuth userInfo: UserInfo,
    ): ApiResponse<UserTravelPreferenceResponse> {
        return ApiResponse.success(
            userApplicationService.getTravelPreference(userInfo.id)
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
    ): ApiResponse<UserDetailResponse> {
        return ApiResponse.success(
            userApplicationService.updateTravelPreference(userInfo.id, request.toServiceRequest())
        )
    }

    @Operation(
        summary = "Update Profile",
        description = "프로필 업데이트 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PatchMapping("/profile")
    fun updateProfile(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: UpdateProfileRequest,
    ): ApiResponse<UpdateProfileResponse> {
        return ApiResponse.success(
            userApplicationService.updateProfile(userInfo.id, request.toServiceRequest())
        )
    }

    @Operation(
        summary = "Delete Profile Image",
        description = "프로필 이미지 삭제 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @DeleteMapping("/profile-image")
    fun deleteProfileImage(
        @GetAuth userInfo: UserInfo,
    ): ApiResponse<Unit> {
        return ApiResponse.success(
            userApplicationService.deleteProfileImage(userInfo.id)
        )
    }
}
