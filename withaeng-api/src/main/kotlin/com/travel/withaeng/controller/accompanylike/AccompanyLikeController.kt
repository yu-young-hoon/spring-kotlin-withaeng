package com.travel.withaeng.controller.accompanylike

import com.travel.withaeng.applicationservice.accompanylike.AccompanyLikeApplicationService
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.resolver.GetAuth
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyLikeController(
    private val accompanyLikeApplicationService: AccompanyLikeApplicationService
) {

    @PostMapping("/{accompanyId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    fun like(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long
    ): ApiResponse<Unit> {
        accompanyLikeApplicationService.like(userInfo.id, accompanyId)
        return ApiResponse.success()
    }

    @DeleteMapping("/{accompanyId}/like")
    fun dislike(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long
    ): ApiResponse<Unit> {
        accompanyLikeApplicationService.dislike(userInfo.id, accompanyId)
        return ApiResponse.success()
    }

}