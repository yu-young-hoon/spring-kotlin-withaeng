package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.*
import java.time.LocalDate

data class SearchAccompanyQuery(
    val sort: AccompanySort? = null,
    val status: AccompanyStatus? = null,
    val continent: Continent? = null,
    val country: Country? = null,
    val city: City? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val minMemberCount: Int? = null,
    val maxMemberCount: Int? = null,
    val minAllowedAge: AccompanyAge? = null,
    val maxAllowedAge: AccompanyAge? = null,
    val preferGender: AccompanyPreferGender? = null,
)