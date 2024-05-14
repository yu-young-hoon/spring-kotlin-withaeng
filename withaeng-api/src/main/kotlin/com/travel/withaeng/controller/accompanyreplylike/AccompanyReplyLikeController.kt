package com.travel.withaeng.controller.accompanyreplylike

import com.travel.withaeng.applicationservice.accompanyreplylike.AccompanyReplyLikeApplicationService
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.resolver.GetAuth
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyReplyLikeController(
    private val accompanyReplyLikeApplicationService: AccompanyReplyLikeApplicationService
) {

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