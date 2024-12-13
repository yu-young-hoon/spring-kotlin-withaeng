package com.withaeng.api.controller.auth

import com.withaeng.api.applicationservice.auth.AuthService
import com.withaeng.api.applicationservice.auth.dto.UserResponse
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.controller.auth.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
) {
    @Operation(summary = "Sign Up API", description = "회원가입 API")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ApiResponse<UserResponse> {
        return ApiResponse.success(
            authService.signUp(request.toServiceRequest())
        )
    }

    @Operation(summary = "Sign In API", description = "로그인 API")
    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ApiResponse<UserResponse> {
        return ApiResponse.success(
            authService.signIn(request.toServiceRequest())
        )
    }

//    @Operation(summary = "Sign In For OAUTH API", description = "OAUTH 로그인 API")
//    @PostMapping("/oauth")
//    fun signInOauth(@RequestBody request: SignInAuthRequest): Unit {
//        return ApiResponse.success(
//            authService.signInForOAuth(request.toServiceRequest())
//        )
//    }

    @Operation(summary = "Re-sending Email API", description = "이메일 재전송 API")
    @PostMapping("/re-send")
    fun resendEmail(@RequestBody request: ResendEmailRequest): ApiResponse<Unit> {
        return ApiResponse.success(
            authService.resendEmail(request.toServiceRequest())
        )
    }

    @Operation(summary = "Email Verify API", description = "이메일 인증 API")
    @PutMapping("/validate-email")
    fun verifyEmail(@RequestBody request: VerifyEmailRequest): ApiResponse<Unit> {
        authService.verifyEmail(request.toServiceRequest())
        return ApiResponse.success()
    }

    @Operation(summary = "Send Mail For Changing Password API", description = "비밀번호 변경을 위한 이메일 전송")
    @PostMapping("/send-email-for-change-password")
    fun sendEmailForChangingPassword(@RequestBody request: SendEmailForChangePasswordRequest): ApiResponse<Unit> {
        authService.sendEmailForChangingPassword(request.toServiceRequest())
        return ApiResponse.success()
    }

    @Operation(summary = "Change Password API", description = "비밀번호 변경 API")
    @PutMapping("/change-password")
    fun changePassword(@RequestBody request: ChangePasswordRequest): ApiResponse<Unit> {
        authService.changePassword(request.toServiceRequest())
        return ApiResponse.success()
    }
}
