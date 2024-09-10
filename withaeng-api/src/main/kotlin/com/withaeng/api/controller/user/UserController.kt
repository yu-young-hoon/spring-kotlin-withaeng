package com.withaeng.api.controller.user

import com.withaeng.api.applicationservice.user.UserApplicationService
import com.withaeng.api.applicationservice.user.dto.UserDetailsResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.controller.user.dto.UpdateTravelPreferenceRequest
import com.withaeng.api.controller.user.dto.toServiceRequest
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User", description = "유저 정보 관리 API")
@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userApplicationService: UserApplicationService) {

    @Operation(
        summary = "Add Details",
        description = "유저 관련 정보 수정 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PostMapping("/travel-preference")
    fun updateTravelPreference(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: UpdateTravelPreferenceRequest,
    ): ApiResponse<UserDetailsResponse> {
        return ApiResponse.success(
            userApplicationService.updateTravelPreference(request.toServiceRequest(userInfo.id))
        )
    }

}
