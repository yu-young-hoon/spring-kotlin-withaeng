package com.travel.withaeng.controller.user

import com.travel.withaeng.applicationservice.user.UserApplicationService
import com.travel.withaeng.applicationservice.user.dto.UserDetailsResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.user.dto.AddUserDetailsRequest
import com.travel.withaeng.controller.user.dto.toServiceRequest
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.resolver.GetAuth
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
    @PostMapping("/add-details")
    fun addDetails(
        @GetAuth userInfo: UserInfo,
        @RequestBody @Valid request: AddUserDetailsRequest
    ): ApiResponse<UserDetailsResponse> {
        return ApiResponse.success(
            userApplicationService.addDetails(request.toServiceRequest(userInfo.id))
        )
    }

}
