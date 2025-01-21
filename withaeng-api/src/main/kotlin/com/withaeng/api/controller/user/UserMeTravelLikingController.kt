package com.withaeng.api.controller.user

import com.withaeng.api.applicationservice.user.UserTravelLikingApplicationService
import com.withaeng.api.applicationservice.user.dto.UserTravelLikingResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.common.IdResponse
import com.withaeng.api.controller.user.dto.PutTravelLikingRequest
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
class UserMeTravelLikingController(private val userTravelLikingApplicationService: UserTravelLikingApplicationService) {
    @Operation(
        summary = "Get User Licking",
        description = "유저 상세 정보 조회 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @GetMapping("/travel-liking")
    fun getTravelLiking(
        @GetAuth userInfo: UserInfo,
    ): ApiResponse<UserTravelLikingResponse> {
        return ApiResponse.success(
            userTravelLikingApplicationService.getTravelLiking(userInfo.id)
        )
    }

    @Operation(
        summary = "Replace Travel Licking",
        description = "여행 선호 정보 업데이트 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/travel-liking")
    fun putTravelLiking(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: PutTravelLikingRequest,
    ): ApiResponse<IdResponse> {
        return ApiResponse.success(
            userTravelLikingApplicationService.updateTravelLiking(userInfo.id, request.toServiceRequest())
        )
    }
}
