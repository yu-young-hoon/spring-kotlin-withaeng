package com.travel.withaeng.controller.accompanyreply

import com.travel.withaeng.common.ApiResponse
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyService
import com.travel.withaeng.domain.accompanyreply.CreateAccompanyReplyDTO
import com.travel.withaeng.domain.accompanyreply.DeleteAccompanyReplyDTO
import com.travel.withaeng.domain.accompanyreply.ModifyAccompanyReplyDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/accompany/{accompanyId}/reply")
class AccompanyReplyController(private val accompanyReplyService: AccompanyReplyService) {

    @PostMapping("")
    fun create(@RequestBody param: CreateAccompanyReplyDTO): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse(true, accompanyReplyService.createAccompanyReply(param), null))
    }

    @PutMapping("/{replyId}")
    fun create(@RequestBody param: ModifyAccompanyReplyDTO): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse(true, accompanyReplyService.modifyAccompanyReply(param), null))
    }

    @DeleteMapping("/{replyId}")
    fun create(@RequestBody param: DeleteAccompanyReplyDTO): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponse(true, accompanyReplyService.deleteAccompanyReply(param), null))
    }

    @GetMapping("/getList")
    fun create(@PathVariable(name = "accompanyId") param: Long): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse(true, accompanyReplyService.getList(param), null))
    }

}