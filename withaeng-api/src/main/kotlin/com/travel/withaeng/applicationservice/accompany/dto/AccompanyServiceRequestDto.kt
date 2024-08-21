package com.travel.withaeng.applicationservice.accompany.dto

import com.travel.withaeng.domain.accompany.*
import com.travel.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyServiceRequest(
    val userId: Long,
    val title: String,
    val content: String,
    val continent: String,
    val country: String,
    val city: String,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val tagIds: List<Long>? = null,
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

fun CreateAccompanyServiceRequest.toDomainDto(): CreateAccompanyDto = CreateAccompanyDto(
    userId = userId,
    title = title,
    content = content,
    destination = AccompanyDestination(
        continent = Continent.valueOf(continent),
        country = Country.valueOf(country),
        city = City.valueOf(city)
    ),
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    tagIds = tagIds?.toSet(),
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = startAccompanyAge,
    endAccompanyAge = endAccompanyAge,
    preferGender = preferGender,
)

data class UpdateAccompanyServiceRequest(
    val accompanyId: Long,
    val userId: Long,
    val title: String? = null,
    val content: String? = null,
    val continent: String? = null,
    val country: String? = null,
    val city: String? = null,
    val startTripDate: LocalDate? = null,
    val endTripDate: LocalDate? = null,
    val bannerImageUrl: String? = null,
    val memberCount: Long? = null,
    val tagIds: List<Long>? = null,
    val openKakaoUrl: String? = null
)

fun UpdateAccompanyServiceRequest.toDomainDto(): UpdateAccompanyDto {
    val destination = if (continent == null || country == null || city == null) {
        null
    } else {
        AccompanyDestination(
            continent = Continent.valueOf(continent),
            country = Country.valueOf(country),
            city = City.valueOf(city)
        )
    }
    return UpdateAccompanyDto(
        accompanyId = accompanyId,
        title = title,
        content = content,
        destination = destination,
        startTripDate = startTripDate,
        endTripDate = endTripDate,
        bannerImageUrl = bannerImageUrl,
        memberCount = memberCount,
        tagIds = tagIds?.toSet(),
        openKakaoUrl = openKakaoUrl
    )
}

data class SearchAccompanyServiceRequest(
    val orderBy: String,
    val isDescending: Boolean,
    val pageIndex: Long,
    val pageSize: Long
)