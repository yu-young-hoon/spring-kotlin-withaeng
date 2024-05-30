package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.travel.withaeng.domain.accompanyreply.QAccompanyReply.accompanyReply
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class AccompanyReplyRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : AccompanyReplyRepositoryCustom {
    override fun search(accompanyId: Long, pageable: Pageable): Page<AccompanyReplyDto> {
        val contents = queryFactory
            .select(
                QAccompanyReplyDto(
                    accompanyReply.id,
                    accompanyReply.userId,
                    accompanyReply.accompanyId,
                    accompanyReply.parentId,
                    accompanyReply.content
                )
            )
            .from(accompanyReply)
            .where(accompanyIdEq(accompanyId))
            .fetch()

        val countQuery = queryFactory
            .select(accompanyReply.count())
            .from(accompanyReply)
            .where(accompanyIdEq(accompanyId))

        return PageableExecutionUtils.getPage(contents, pageable) { countQuery.fetchOne() ?: 0L }
    }

    private fun accompanyIdEq(accompanyId: Long): BooleanExpression? {
        return accompanyReply.accompanyId.eq(accompanyId)
    }
}