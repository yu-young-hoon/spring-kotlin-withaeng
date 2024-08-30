package com.withaeng.api.controller.test

import com.withaeng.api.applicationservice.test.TestApplicationService
import com.withaeng.api.common.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Test", description = "테스트를 위한 API")
@RequestMapping("/api/v1/test")
@RestController
class TestController(
    private val testApplicationService: TestApplicationService
) {

    @Operation(summary = "Provide Test Access Token", description = "테스트 토큰 발급")
    @GetMapping("/token/{userId}")
    fun getToken(@PathVariable userId: Long): ApiResponse<String> {
        return ApiResponse.success(testApplicationService.provideUserToken(userId))
    }

    @Operation(summary = "Grant User Role", description = "사용자 인증 처리")
    @PostMapping("/user/{userId}/confirm")
    fun confirmUser(@PathVariable userId: Long): ApiResponse<Unit> {
        testApplicationService.confirmUser(userId)
        return ApiResponse.success()
    }


}