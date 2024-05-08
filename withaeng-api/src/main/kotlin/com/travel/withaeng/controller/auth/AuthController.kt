package com.travel.withaeng.controller.auth

import com.travel.withaeng.applicationservice.auth.AuthApplicationService
import com.travel.withaeng.applicationservice.auth.dto.UserResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.auth.dto.SignInRequest
import com.travel.withaeng.controller.auth.dto.SignUpRequest
import com.travel.withaeng.controller.auth.dto.ValidateEmailRequest
import com.travel.withaeng.controller.auth.dto.toServiceRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Auth", description = "Auth API")
@RestController
@RequestMapping("api/v1/auth")
class AuthController(private val authApplicationService: AuthApplicationService) {

    @Operation(summary = "Sign Up API", description = "회원가입 API")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ApiResponse<UserResponse> {
        return ApiResponse.success(
            authApplicationService.signUp(request.toServiceRequest())
        )
    }

    @Operation(summary = "Sign In API", description = "로그인 API")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ApiResponse<UserResponse> {
        return ApiResponse.success(
            authApplicationService.signIn(request.toServiceRequest())
        )
    }

    @Operation(summary = "Validating Email API", description = "이메일 인증 API")
    @PutMapping("/validate-email")
    fun validateEmail(@RequestBody request: ValidateEmailRequest): ApiResponse<Unit> {
        authApplicationService.validateEmail(request.toServiceRequest())
        return ApiResponse.success()
    }
}