package com.travel.withaeng.controller.accompanyreply

import com.travel.withaeng.applicationservice.accompanyreply.AccompanyReplyApplicationService
import com.travel.withaeng.applicationservice.accompanyreply.dto.AccompanyReplyResponse
import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.controller.accompanyreply.dto.CreateAccompanyReplyRequest
import com.travel.withaeng.controller.accompanyreply.dto.UpdateAccompanyReplyRequest
import com.travel.withaeng.controller.accompanyreply.dto.toServiceRequest
import com.travel.withaeng.security.authentication.UserInfo
import com.travel.withaeng.security.resolver.GetAuth
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Accompany Reply", description = "동행 댓글 API")
@RestController
@RequestMapping("/api/v1/accompany")
class AccompanyReplyController(
    private val accompanyReplyApplicationService: AccompanyReplyApplicationService
) {

    @Operation(
        summary = "Create Accompany Reply API",
        description = "동행 댓글 생성 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
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

    @Operation(summary = "Retrieve All Accompany Replies API", description = "동행 댓글 조회 API")
    @GetMapping("/{accompanyId}/reply/search")
    fun search(
        @PathVariable(name = "accompanyId") accompanyId: Long,
        pageable: Pageable
    ): ApiResponse<List<AccompanyReplyResponse>> {
        return ApiResponse.success(
            accompanyReplyApplicationService.search(accompanyId = accompanyId, pageable = pageable)
        )
    }

    @Operation(
        summary = "Update Accompany Reply API",
        description = "동행 댓글 수정 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
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

    @Operation(
        summary = "Delete Accompany Reply API",
        description = "동행 댓글 삭제 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @DeleteMapping("/{accompanyId}/reply/{replyId}")
    fun delete(
        @GetAuth userInfo: UserInfo,
        @PathVariable("replyId") replyId: Long
    ): ApiResponse<Unit> {
        return ApiResponse.success(
            accompanyReplyApplicationService.delete(userId = userInfo.id, accompanyReplyId = replyId)
        )
    }

    @Operation(
        summary = "Create Accompany Sub Reply API",
        description = "동행 댓글의 댓글 생성 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PostMapping("/{accompanyId}/reply/{replyId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSubReply(
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

    @Operation(
        summary = "Update Accompany Sub Reply API",
        description = "동행 댓글의 댓글 수정 API",
        security = [SecurityRequirement(name = "Authorization")]
    )
    @PutMapping("/{accompanyId}/reply/{replyId}/{subReplyId}")
    fun updateSubReply(
        @GetAuth userInfo: UserInfo,
        @PathVariable("accompanyId") accompanyId: Long,
        @PathVariable("replyId") replyId: Long,
        @PathVariable("subReplyId") subReplyId: Long,
        @RequestBody request: UpdateAccompanyReplyRequest
    ): ApiResponse<AccompanyReplyResponse> {
        return ApiResponse.success(
            accompanyReplyApplicationService.updateSubReply(
                request.toServiceRequest(
                    userId = userInfo.id,
                    accompanyId = accompanyId,
                    accompanyReplyId = subReplyId,
                    parentId = replyId,
                )
            )
        )
    }

}