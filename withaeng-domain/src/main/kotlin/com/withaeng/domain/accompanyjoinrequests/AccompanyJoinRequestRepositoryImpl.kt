package com.withaeng.domain.accompanyjoinrequests

import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.withaeng.domain.accompanyjoinrequests.dto.FindAccompanyJoinRequestDto
import com.withaeng.domain.accompanyjoinrequests.dto.QFindAccompanyJoinRequestDto
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequestStatus
import com.withaeng.domain.accompanyrequests.QAccompanyJoinRequest.accompanyJoinRequest
import com.withaeng.domain.user.QUser.user


class AccompanyJoinRequestRepositoryImpl(

    private val queryFactory: JPAQueryFactory,

    ) : AccompanyJoinRequestRepositoryCustom {

    override fun findJoinRequestsByAccompanyId(accompanyId: Long): List<FindAccompanyJoinRequestDto> {
        return queryFactory
            .select(
                QFindAccompanyJoinRequestDto(
                    accompanyJoinRequest.id,
                    user.id,
                    accompanyJoinRequest.status,
                    user.profile.nickname,
                    user.profile.profileImageUrl,
                    user.gender,
                    user.profile.introduction,
                    user.createdAt,
                    user.birth,
                    user.mannerScore,
                ),
            )
            .from(accompanyJoinRequest)
            .innerJoin(user)
            .on(accompanyJoinRequest.userId.eq(user.id))
            .where(
                accompanyJoinRequest.accompany.id.eq(accompanyId),
            )
            .orderBy(findJoinRequestsSort())
            .fetch()
    }

    private fun findJoinRequestsSort() = CaseBuilder()
        .`when`(accompanyJoinRequest.status.eq(AccompanyJoinRequestStatus.WAIT)).then(1)
        .`when`(accompanyJoinRequest.status.eq(AccompanyJoinRequestStatus.ACCEPT)).then(2)
        .`when`(accompanyJoinRequest.status.eq(AccompanyJoinRequestStatus.REJECT)).then(3)
        .otherwise(4)
        .asc()
}