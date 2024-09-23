package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.AccompanyAge
import com.withaeng.domain.accompany.AccompanyPreferGender
import com.withaeng.domain.accompany.AccompanySort
import com.withaeng.domain.accompany.AccompanyStatus
import com.withaeng.domain.destination.City
import com.withaeng.domain.destination.Continent
import com.withaeng.domain.destination.Country
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