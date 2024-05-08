package com.travel.withaeng.domain.accompany

interface AccompanyRepositoryCustom {

    fun getAccompany(accompanyId: Long): GetDTO?

    fun getAccompanyList(param: SearchAccompanyDTO): List<GetDTO>
}