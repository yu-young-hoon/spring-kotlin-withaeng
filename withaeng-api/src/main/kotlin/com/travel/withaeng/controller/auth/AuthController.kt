package com.travel.withaeng.controller.auth

import com.travel.withaeng.applicationservice.auth.AuthApplicationService
import com.travel.withaeng.applicationservice.auth.dto.UserResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.auth.dto.SignUpRequest
import com.travel.withaeng.controller.auth.dto.toServiceRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(private val authApplicationService: AuthApplicationService) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ApiResponse<UserResponse> {
        return ApiResponse.success(
            authApplicationService.signUp(request.toServiceRequest())
        )
    }
}