package com.travel.withaeng.applicationservice.accompanyreply.dto

import com.travel.withaeng.applicationservice.common.Paging
import com.travel.withaeng.applicationservice.common.PagingResponse
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyDto

data class AccompanyReplyResponse(
    val id: Long,
    val accompanyId: Long,
    val parentId: Long? = null,
    val userId: Long,
    val content: String,
    val likeCount: Long = 0L
)

class PagingAccompanyReplyResponse(
    private val content: List<AccompanyReplyResponse>,
    private val paging: Paging
) : PagingResponse<List<AccompanyReplyResponse>> {
    override fun getPaging(): Paging = paging

    override fun getContent(): List<AccompanyReplyResponse> = content
}

fun AccompanyReplyDto.toResponse(likeCount: Long = 0L): AccompanyReplyResponse {
    return AccompanyReplyResponse(
        id = id,
        accompanyId = accompanyId,
        parentId = parentId,
        userId = userId,
        content = content,
        likeCount = likeCount
    )
}