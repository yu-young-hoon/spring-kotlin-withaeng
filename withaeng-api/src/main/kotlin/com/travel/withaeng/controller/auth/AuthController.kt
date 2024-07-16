package com.travel.withaeng.controller.auth

import com.travel.withaeng.applicationservice.auth.AuthApplicationService
import com.travel.withaeng.applicationservice.auth.dto.UserResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.auth.dto.ChangePasswordRequest
import com.travel.withaeng.controller.auth.dto.ResendEmailRequest
import com.travel.withaeng.controller.auth.dto.SendEmailForChangePasswordRequest
import com.travel.withaeng.controller.auth.dto.SignInRequest
import com.travel.withaeng.controller.auth.dto.SignUpRequest
import com.travel.withaeng.controller.auth.dto.ValidateEmailRequest
import com.travel.withaeng.controller.auth.dto.toServiceRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/api/v1/auth")
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

    @Operation(summary = "Re-sending Email API", description = "이메일 재전송 API")
    @PostMapping("/re-send")
    fun resendEmail(@RequestBody request: ResendEmailRequest): ApiResponse<Unit> {
        return ApiResponse.success(
            authApplicationService.resendEmail(request.toServiceRequest())
        )
    }

    @Operation(summary = "Validating Email API", description = "이메일 인증 API")
    @PutMapping("/validate-email")
    fun validateEmail(@RequestBody request: ValidateEmailRequest): ApiResponse<Unit> {
        authApplicationService.validateEmail(request.toServiceRequest())
        return ApiResponse.success()
    }

    @Operation(summary = "Send Mail For Changing Password API", description = "비밀번호 변경을 위한 이메일 전송")
    @PostMapping("/send-email-for-change-password")
    fun sendEmailForChangingPassword(@RequestBody request: SendEmailForChangePasswordRequest): ApiResponse<Unit> {
        authApplicationService.sendEmailForChangingPassword(request.toServiceRequest())
        return ApiResponse.success()
    }

    @Operation(summary = "Change Password API", description = "비밀번호 변경 API")
    @PutMapping("/change-password")
    fun changePassword(@RequestBody request: ChangePasswordRequest): ApiResponse<Unit> {
        authApplicationService.changePassword(request.toServiceRequest())
        return ApiResponse.success()
    }
}
