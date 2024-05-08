package com.travel.withaeng.domain.accompanyreplylike

interface AccompanyReplyLikeRepositoryCustom {

    fun getAccompanyReplyLikeList(replyIds: List<Long>): List<GetReplyLikeDTO>

}