package com.travel.withaeng.domain.accompany

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.travel.withaeng.domain.accompany.QAccompany.accompany
import com.travel.withaeng.domain.accompanylike.QAccompanyLike.accompanyLike
import com.travel.withaeng.domain.user.QUser.user

class AccompanyRepositoryImpl(

    private val queryFactory: JPAQueryFactory

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
                    accompany.viewCount,
                    accompany.openKakaoUrl,
                    accompany.startAccompanyAge,
                    accompany.endAccompanyAge,
                    accompany.preferGender,
                    accompany.tagIds,
                    accompanyLike.count(),
                    QFindAccompanyUserInfoDto(
                        user.nickname,
                        user.profileImageUrl,
                        user.isMale,
                        user.bio,
                        user.createdAt,
                    ),
                )
            )
            .from(accompany)
            .leftJoin(accompanyLike)
            .on(accompany.id.eq(accompanyLike.accompanyId))
            .innerJoin(user)
            .on(accompany.userId.eq(user.id))
            .where(
                accompanyIdEq(accompanyId)
            )
            .groupBy(accompanyLike.accompanyId)
            .fetchOne()
    }

    private fun accompanyIdEq(accompanyId: Long): BooleanExpression? {
        return accompany.id.eq(accompanyId)
    }
}
