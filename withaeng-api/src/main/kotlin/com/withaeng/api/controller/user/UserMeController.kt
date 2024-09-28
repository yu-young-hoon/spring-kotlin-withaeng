package com.withaeng.api.controller.user

import com.withaeng.api.applicationservice.user.UserApplicationService
import com.withaeng.api.applicationservice.user.dto.PutProfileImageResponse
import com.withaeng.api.applicationservice.user.dto.UserStatisticalProfileResponse
import com.withaeng.api.applicationservice.user.dto.UserTravelPreferenceResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.common.IdResponse
import com.withaeng.api.controller.user.dto.PatchNicknameRequest
import com.withaeng.api.controller.user.dto.PutIntroductionRequest
import com.withaeng.api.controller.user.dto.PutTravelPreferenceRequest
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
        summary = "Replace Travel Preference",
        description = "여행 선호 정보 업데이트 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/travel-preference")
    fun putTravelPreference(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: PutTravelPreferenceRequest,
    ): ApiResponse<IdResponse> {
        return ApiResponse.success(
            userApplicationService.updateTravelPreference(userInfo.id, request.toServiceRequest())
        )
    }

    @Operation(
        summary = "Replace Introduction",
        description = "이전 소개글은 삭제됩니다.",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/introduction")
    fun putIntroduction(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: PutIntroductionRequest,
    ): ApiResponse<IdResponse> {
        return ApiResponse.success(
            userApplicationService.putIntroduction(userInfo.id, request.introduction)
        )
    }

    @Operation(
        summary = "Replace Profile Image",
        description = "이전 프로필 이미지는 삭제됩니다.",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/profile-image")
    fun putProfileImage(
        @GetAuth userInfo: UserInfo,
    ): ApiResponse<PutProfileImageResponse> {
        return ApiResponse.success(
            userApplicationService.putProfileImage(userInfo.id)
        )
    }

    @Operation(
        summary = "Update Nickname",
        description = "nickname 값이 null 이면 기존 닉네임이 유지됩니다.",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PatchMapping("/nickname")
    fun patchNickname(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: PatchNicknameRequest,
    ): ApiResponse<IdResponse> {
        return ApiResponse.success(
            userApplicationService.updateNickname(userInfo.id, request.nickname)
        )
    }

    @Operation(
        summary = "Delete Profile Image",
        description = "",
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
