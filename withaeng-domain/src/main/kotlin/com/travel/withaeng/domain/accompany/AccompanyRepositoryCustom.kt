package com.travel.withaeng.domain.accompany

interface AccompanyRepositoryCustom {
    fun findAccompanyDetail(accompanyId: Long): FindAccompanyDto?
}