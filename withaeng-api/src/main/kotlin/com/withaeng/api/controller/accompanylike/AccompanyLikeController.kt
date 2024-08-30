package com.withaeng.api.controller.accompanylike

import com.withaeng.api.applicationservice.accompanylike.AccompanyLikeApplicationService
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Like/Dislike Accompany", description = "동행 좋아요 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyLikeController(
    private val accompanyLikeApplicationService: AccompanyLikeApplicationService
) {

    @Operation(
        summary = "Like Accompany API",
        description = "동행 게시글 좋아요 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PostMapping("/{accompanyId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun like(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long
    ): ApiResponse<Unit> {
        accompanyLikeApplicationService.like(userInfo.id, accompanyId)
        return ApiResponse.success()
    }

    @Operation(
        summary = "Dislike Accompany API",
        description = "동행 게시글 좋아요 취소 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @DeleteMapping("/{accompanyId}/like")
    fun dislike(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long
    ): ApiResponse<Unit> {
        accompanyLikeApplicationService.dislike(userInfo.id, accompanyId)
        return ApiResponse.success()
    }

}