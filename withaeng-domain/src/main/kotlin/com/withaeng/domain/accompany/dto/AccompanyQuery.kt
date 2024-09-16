package com.withaeng.domain.accompany.dto

import com.withaeng.domain.accompany.AccompanyAge
import com.withaeng.domain.accompany.City
import com.withaeng.domain.accompany.Continent
import com.withaeng.domain.accompany.Country
import com.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class SearchAccompanyQuery(
    val continent: Continent? = null,
    val country: Country? = null,
    val city: City? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val minMemberCount: Int? = null,
    val maxMemberCount: Int? = null,
    val minAllowedAge: AccompanyAge? = null,
    val maxAllowedAge: AccompanyAge? = null,
    val preferGender: UserPreferAccompanyGender? = null,
)