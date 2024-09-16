package com.withaeng.domain.accompany

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.withaeng.domain.accompany.QAccompany.accompany
import com.withaeng.domain.accompany.dto.*
import com.withaeng.domain.accompanylike.QAccompanyLike.accompanyLike
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequestStatus
import com.withaeng.domain.accompanyrequests.QAccompanyJoinRequest.accompanyJoinRequest
import com.withaeng.domain.accompanystatistics.QAccompanyStatistics.accompanyStatistics
import com.withaeng.domain.user.QUser.user
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import java.time.LocalDate

class AccompanyRepositoryImpl(

    private val queryFactory: JPAQueryFactory,

    ) : AccompanyRepositoryCustom {

    override fun findAccompanyDetail(accompanyId: Long): FindAccompanyDto? {
        return queryFactory
            .select(
                QFindAccompanyDto(
                    accompany.id,
                    accompany.userId,
                    accompany.title,
                    accompany.content,
                    accompany.accompanyDestination,
                    accompany.startTripDate,
                    accompany.endTripDate,
                    accompany.bannerImageUrl,
                    accompany.memberCount,
                    accompanyStatistics.viewCount,
                    accompany.openKakaoUrl,
                    accompany.startAccompanyAge,
                    accompany.endAccompanyAge,
                    accompany.preferGender,
                    accompany.tags,
                    accompanyLike.count(),
                    QFindAccompanyUserInfoDto(
                        user.profile.nickname,
                        user.profile.profileImageUrl,
                        user.gender,
                        user.profile.introduction,
                        user.createdAt,
                    ),
                )
            )
            .from(accompany)
            .leftJoin(accompanyLike)
            .on(accompany.id.eq(accompanyLike.accompanyId))
            .innerJoin(accompanyStatistics)
            .on(accompany.id.eq(accompanyStatistics.accompany.id))
            .innerJoin(user)
            .on(accompany.userId.eq(user.id))
            .where(
                accompanyIdEq(accompanyId)
            )
            .groupBy(accompanyLike.accompanyId)
            .fetchOne()
    }

    override fun searchAccompanies(pageable: Pageable, query: SearchAccompanyQuery): Page<SearchAccompanyDto> {
        val result = queryFactory
            .select(
                QSearchAccompanyDto(
                    accompany.id,
                    accompany.bannerImageUrl,
                    accompany.accompanyStatus,
                    accompany.startTripDate,
                    accompany.endTripDate,
                    accompanyJoinRequest.count(),
                    accompany.memberCount,
                    accompany.title,
                    accompany.tags,
                    QSearchAccompanyHostDto(
                        user.id,
                        user.profile.nickname,
                        user.profile.profileImageUrl,
                    ),
                )
            )
            .from(accompany)
            .innerJoin(user)
            .on(accompany.userId.eq(user.id))
            .leftJoin(accompanyJoinRequest).on(
                accompanyJoinRequest.accompany.eq(accompany)
                    .and(accompanyJoinRequest.status.eq(AccompanyJoinRequestStatus.ACCEPT))
            )
            .groupBy(
                accompany.id,
                accompany.bannerImageUrl,
                accompany.accompanyStatus,
                user.id,
                user.profile.nickname,
                user.profile.profileImageUrl,
                accompany.startTripDate,
                accompany.endTripDate,
                accompany.memberCount,
                accompany.title,
                accompany.tags
            )
            .where(
                continentEq(query.continent),
                countryEq(query.country),
                cityEq(query.city),
                containTripDate(query.startDate, query.endDate),
                containMemberCount(query.minMemberCount, query.maxMemberCount),
                containAllowedAge(query.minAllowedAge, query.maxAllowedAge),
                preferGenderEq(query.preferGender),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val countQuery = queryFactory
            .select(accompany.id.count())
            .from(accompany)
            .innerJoin(user)
            .on(accompany.userId.eq(user.id))
            .leftJoin(accompanyJoinRequest).on(
                accompanyJoinRequest.accompany.eq(accompany)
                    .and(accompanyJoinRequest.status.eq(AccompanyJoinRequestStatus.ACCEPT))
            )
            .where(
                continentEq(query.continent),
                countryEq(query.country),
                cityEq(query.city),
                containTripDate(query.startDate, query.endDate),
                containMemberCount(query.minMemberCount, query.maxMemberCount),
                containAllowedAge(query.minAllowedAge, query.maxAllowedAge),
                preferGenderEq(query.preferGender),
            )

        return PageableExecutionUtils.getPage(result, pageable) { countQuery.fetchOne() ?: 0L }
    }

    private fun continentEq(continent: Continent?): BooleanExpression? {
        return continent?.let { accompany.accompanyDestination.continent.eq(it) }
    }

    private fun countryEq(country: Country?): BooleanExpression? {
        return country?.let { accompany.accompanyDestination.country.eq(it) }
    }

    private fun cityEq(city: City?): BooleanExpression? {
        return city?.let { accompany.accompanyDestination.city.eq(it) }
    }

    private fun containTripDate(startDate: LocalDate?, endDate: LocalDate?): BooleanExpression? {
        return startDate?.let { accompany.endTripDate.goe(startDate) }
            ?.and(endDate?.let { accompany.startTripDate.loe(endDate) })
    }

    private fun containMemberCount(minMemberCount: Int?, maxMemberCount: Int?): BooleanExpression? {
        return minMemberCount?.let { accompany.memberCount.goe(minMemberCount) }
            ?.and(maxMemberCount?.let { accompany.memberCount.loe(maxMemberCount) })
    }

    private fun containAllowedAge(minAllowedAge: AccompanyAge?, maxAllowedAge: AccompanyAge?): BooleanExpression? {
        return minAllowedAge?.let { accompany.endAccompanyAge.goe(minAllowedAge.value) }
            ?.and(maxAllowedAge?.let { accompany.startAccompanyAge.loe(maxAllowedAge.value) })
    }

    private fun preferGenderEq(preferGender: AccompanyPreferGender?): BooleanExpression? {
        return preferGender?.let { accompany.preferGender.eq(it) }
    }

    private fun accompanyIdEq(accompanyId: Long): BooleanExpression {
        return accompany.id.eq(accompanyId)
    }
}
