package com.travel.withaeng.domain.accompany

import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyRepository : JpaRepository<AccompanyEntity, Long>, AccompanyRepositoryCustom {

    fun findByAccompanyId(accompanyId: Long): AccompanyEntity?

}