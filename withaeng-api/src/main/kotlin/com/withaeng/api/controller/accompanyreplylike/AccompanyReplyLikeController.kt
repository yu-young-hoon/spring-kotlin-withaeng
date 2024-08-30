package com.withaeng.api.controller.accompanyreplylike

import com.withaeng.api.applicationservice.accompanyreplylike.AccompanyReplyLikeApplicationService
import com.withaeng.api.common.ApiResponse
import com.withaeng.api.security.authentication.UserInfo
import com.withaeng.api.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Accompany Reply Like", description = "동행 댓글 좋아요 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyReplyLikeController(
    private val accompanyReplyLikeApplicationService: AccompanyReplyLikeApplicationService
) {

    @Operation(
        summary = "Like Accompany Reply API",
        description = "동행 댓글 좋아요 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PostMapping("/{accompanyId}/reply/{replyId}/like")
    fun create(
        @GetAuth userInfo: UserInfo,
        @PathVariable("replyId") replyId: Long
    ): ApiResponse<Unit> {
        accompanyReplyLikeApplicationService.like(
            userId = userInfo.id,
            accompanyReplyId = replyId
        )
        return ApiResponse.success()
    }

    @Operation(
        summary = "Dislike Accompany Reply API",
        description = "동행 댓글 좋아요 취소 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @DeleteMapping("/{accompanyId}/reply/{replyId}/dislike")
    fun delete(
        @GetAuth userInfo: UserInfo,
        @PathVariable("replyId") replyId: Long
    ): ApiResponse<Unit> {
        accompanyReplyLikeApplicationService.dislike(
            userId = userInfo.id,
            accompanyReplyId = replyId
        )
        return ApiResponse.success()
    }

}