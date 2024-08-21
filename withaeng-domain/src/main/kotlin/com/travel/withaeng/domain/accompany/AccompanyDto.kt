package com.travel.withaeng.domain.accompany

import com.travel.withaeng.domain.user.UserPreferAccompanyGender
import java.time.LocalDate

data class CreateAccompanyDto(
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val tagIds: Set<Long>? = emptySet(),
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

data class UpdateAccompanyDto(
    val accompanyId: Long,
    val title: String? = null,
    val content: String? = null,
    val destination: AccompanyDestination? = null,
    val startTripDate: LocalDate? = null,
    val endTripDate: LocalDate? = null,
    val bannerImageUrl: String? = null,
    val memberCount: Long? = null,
    val tagIds: Set<Long>? = null,
    val openKakaoUrl: String? = null
)

data class AccompanyDto(
    val id: Long,
    val userId: Long,
    val title: String,
    val content: String,
    val destination: AccompanyDestination,
    val startTripDate: LocalDate,
    val endTripDate: LocalDate,
    val bannerImageUrl: String? = null,
    val memberCount: Long,
    val viewCount: Long,
    val openKakaoUrl: String,
    val startAccompanyAge: AccompanyAge,
    val endAccompanyAge: AccompanyAge,
    val preferGender: UserPreferAccompanyGender,
)

fun Accompany.toDto(): AccompanyDto = AccompanyDto(
    id = id,
    userId = userId,
    title = title,
    content = content,
    destination = accompanyDestination,
    startTripDate = startTripDate,
    endTripDate = endTripDate,
    bannerImageUrl = bannerImageUrl,
    memberCount = memberCount,
    viewCount = viewCount,
    openKakaoUrl = openKakaoUrl,
    startAccompanyAge = AccompanyAge.fromValue(startAccompanyAge),
    endAccompanyAge = AccompanyAge.fromValue(endAccompanyAge),
    preferGender = preferGender
)

data class SearchAccompanyDto(

    var viewCntOrder: Boolean,//조회수 높은 순서

    var likeCntOrder: Boolean,//좋아요 높은 순서

    var startTripDate: LocalDate,//동행 모집 시작일시

    var endTripDate: LocalDate,//동행 모집 마감일시

    var pageIndex: Long,

    var pageSize: Long

) {
    fun getCurrentPage(): Long {
        return (this.pageIndex - 1) * this.pageSize
    }

}