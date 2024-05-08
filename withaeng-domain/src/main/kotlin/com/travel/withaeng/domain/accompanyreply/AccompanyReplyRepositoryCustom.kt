package com.travel.withaeng.domain.accompanyreply

interface AccompanyReplyRepositoryCustom {

    fun getAccompanyReplyList(accompanyId: Long): List<GetReplyDTO>?

}