package com.withaeng.domain.accompanyjoinrequests

import com.withaeng.domain.accompanyjoinrequests.dto.FindAccompanyJoinRequestDto

interface AccompanyJoinRequestRepositoryCustom {
    fun findJoinRequestsByAccompanyId(accompanyId: Long): List<FindAccompanyJoinRequestDto>
}