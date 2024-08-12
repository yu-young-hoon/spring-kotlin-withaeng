package com.travel.withaeng.applicationservice.accompanyreply.dto

import com.travel.withaeng.applicationservice.common.Paging
import com.travel.withaeng.applicationservice.common.PagingResponse
import com.travel.withaeng.applicationservice.user.dto.UserSimpleResponse
import com.travel.withaeng.applicationservice.user.dto.toSimpleResponse
import com.travel.withaeng.domain.accompanyreply.AccompanyReplyDto
import com.travel.withaeng.domain.user.UserSimpleDto
import java.time.LocalDateTime

data class AccompanyReplyResponse(
    val id: Long,
    val author: UserSimpleResponse,
    val accompanyId: Long,
    val parentId: Long? = null,
    val content: String,
    val likeCount: Long = 0L,
    val createdAt: LocalDateTime
)

class PagingAccompanyReplyResponse(
    private val content: List<AccompanyReplyResponse>,
    private val paging: Paging
) : PagingResponse<List<AccompanyReplyResponse>> {
    override fun getPaging(): Paging = paging

    override fun getContent(): List<AccompanyReplyResponse> = content
}

fun AccompanyReplyDto.toResponse(userSimpleDto: UserSimpleDto, likeCount: Long = 0L): AccompanyReplyResponse {
    return AccompanyReplyResponse(
        id = id,
        accompanyId = accompanyId,
        parentId = parentId,
        author = userSimpleDto.toSimpleResponse(),
        content = content,
        likeCount = likeCount,
        createdAt = createdAt
    )
}
