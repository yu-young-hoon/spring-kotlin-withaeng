package com.withaeng.domain.accompany

import com.withaeng.domain.accompany.dto.FindAccompanyDto
import com.withaeng.domain.accompany.dto.SearchAccompanyDto
import com.withaeng.domain.accompany.dto.SearchAccompanyQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AccompanyRepositoryCustom {
    fun findAccompanyDetail(accompanyId: Long): FindAccompanyDto?

    fun searchAccompanies(pageable: Pageable, query: SearchAccompanyQuery): Page<SearchAccompanyDto>
}