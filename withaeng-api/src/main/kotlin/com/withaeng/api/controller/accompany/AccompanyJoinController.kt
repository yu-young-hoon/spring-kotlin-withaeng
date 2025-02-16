package com.withaeng.api.controller.accompany

import com.withaeng.api.applicationservice.accompany.AccompanyApplicationService
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Accompany", description = "동행 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyJoinController(
    private val accompanyApplicationService: AccompanyApplicationService,
) {
    @Operation(
        summary = "Create AccompanyJoinRequests API",
        description = "동행 참가 신청 API - Guest",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PostMapping("/{accompanyId}/join-requests")
    fun requestJoin(
        @GetAuth userInfo: UserInfo,
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
    ): ApiResponse<Unit> {
        accompanyApplicationService.requestJoin(
            accompanyId = accompanyId, userId = userInfo.id
        )
        return ApiResponse.success()
    }

    @Operation(
        summary = "Create AccompanyJoinRequests API",
        description = "동행 참가 취소 API - Guest",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}/join-requests/{joinRequestId}/cancel")
    fun cancelJoin(
        @GetAuth userInfo: UserInfo,
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
        @Parameter(description = "동행 참가 요청 id") @PathVariable joinRequestId: Long,
    ): ApiResponse<Unit> {
        accompanyApplicationService.cancelJoin(
            accompanyId = accompanyId, userId = userInfo.id, joinRequestId = joinRequestId
        )
        return ApiResponse.success()
    }

    @Operation(
        summary = "Create AccompanyJoinRequests API",
        description = "동행 승인 API - Host",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}/join-requests/{joinRequestId}/accept")
    fun acceptJoin(
        @GetAuth userInfo: UserInfo,
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
        @Parameter(description = "동행 참가 요청 id") @PathVariable joinRequestId: Long,
    ): ApiResponse<Unit> {
        accompanyApplicationService.acceptJoin(
            accompanyId = accompanyId, userId = userInfo.id, joinRequestId = joinRequestId
        )
        return ApiResponse.success()
    }

    @Operation(
        summary = "Create AccompanyJoinRequests API",
        description = "동행 거부 API - Host",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}/join-requests/{joinRequestId}/reject")
    fun rejectJoin(
        @GetAuth userInfo: UserInfo,
        @Parameter(description = "동행 id") @PathVariable accompanyId: Long,
        @Parameter(description = "동행 참가 요청 id") @PathVariable joinRequestId: Long,
    ): ApiResponse<Unit> {
        accompanyApplicationService.rejectJoin(
            accompanyId = accompanyId, userId = userInfo.id, joinRequestId = joinRequestId
        )
        return ApiResponse.success()
    }

}