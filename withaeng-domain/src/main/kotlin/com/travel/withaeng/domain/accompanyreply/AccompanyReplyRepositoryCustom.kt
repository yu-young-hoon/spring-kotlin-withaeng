package com.travel.withaeng.domain.accompanyreply

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AccompanyReplyRepositoryCustom {
    fun search(accompanyId: Long, pageable: Pageable): Page<AccompanyReplyDto>
}