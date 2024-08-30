package com.withaeng.api.applicationservice.accompanyreply.dto

data class CreateAccompanyReplyServiceRequest(
    val userId: Long,
    val accompanyId: Long,
    val content: String,
    val parentId: Long? = null
)

data class UpdateAccompanyReplyServiceRequest(
    val accompanyReplyId: Long,
    val userId: Long,
    val accompanyId: Long,
    val content: String,
    val parentId: Long? = null
)