package com.travel.withaeng.controller.accompanyreply

import com.travel.withaeng.applicationservice.accompanyreply.AccompanyReplyApplicationService
import com.travel.withaeng.applicationservice.accompanyreply.dto.AccompanyReplyResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.accompanyreply.dto.CreateAccompanyReplyRequest
import com.travel.withaeng.controller.accompanyreply.dto.UpdateAccompanyReplyRequest
import com.travel.withaeng.controller.accompanyreply.dto.toServiceRequest
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.resolver.GetAuth
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyReplyController(private val accompanyReplyApplicationService: AccompanyReplyApplicationService) {

    @PostMapping("/{accompanyId}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long,
        @RequestBody request: CreateAccompanyReplyRequest
    ): ApiResponse<AccompanyReplyResponse> {
        return ApiResponse.success(
            accompanyReplyApplicationService.create(
                request.toServiceRequest(
                    userId = userInfo.id,
                    accompanyId = accompanyId
                )
            )
        )
    }

    @PostMapping("/{accompanyId}/reply/{replyId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun reply(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long,
        @PathVariable("replyId") replyId: Long,
        @RequestBody request: CreateAccompanyReplyRequest
    ): ApiResponse<AccompanyReplyResponse> {
        return ApiResponse.success(
            accompanyReplyApplicationService.create(
                request.toServiceRequest(
                    userId = userInfo.id,
                    accompanyId = accompanyId,
                    parentId = replyId
                )
            )
        )
    }

    @PutMapping("/{accompanyId}/reply/{replyId}")
    fun update(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long,
        @PathVariable("replyId") replyId: Long,
        @RequestBody request: UpdateAccompanyReplyRequest
    ): ApiResponse<AccompanyReplyResponse> {
        return ApiResponse.success(
            accompanyReplyApplicationService.update(
                request.toServiceRequest(
                    userId = userInfo.id,
                    accompanyId = accompanyId,
                    accompanyReplyId = replyId
                )
            )
        )
    }

    @DeleteMapping("/{accompanyId}/reply/{replyId}")
    fun delete(
        @GetAuth userInfo: UserInfo,
        @PathVariable("replyId") replyId: Long
    ): ApiResponse<Unit> {
        return ApiResponse.success(
            accompanyReplyApplicationService.delete(userId = userInfo.id, accompanyReplyId = replyId)
        )
    }

    @GetMapping("/{accompanyId}/reply/all")
    fun findAll(@PathVariable(name = "accompanyId") accompanyId: Long): ApiResponse<List<AccompanyReplyResponse>> {
        return ApiResponse.success(
            accompanyReplyApplicationService.findAll(accompanyId = accompanyId)
        )
    }

}