package com.travel.withaeng.domain.accompanyreply

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.travel.withaeng.domain.accompanyreply.QAccompanyReply.accompanyReply
import com.travel.withaeng.domain.accompanyreplylike.QAccompanyReplyLike.accompanyReplyLike
import com.travel.withaeng.domain.user.QUser.user
import com.travel.withaeng.domain.user.QUserBasicInfoDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class AccompanyReplyRepositoryImpl(

    private val queryFactory: JPAQueryFactory

) : AccompanyReplyRepositoryCustom {

    override fun findAccompanyReplyList(
        accompanyId: Long,
        pageable: Pageable,
    ): Page<FindAccompanyReplyDto> {

        val contents = queryFactory
            .select(
                QFindAccompanyReplyDto(
                    accompanyReply.id,
                    accompanyReply.accompanyId,
                    accompanyReply.parentId,
                    accompanyReply.content,
                    accompanyReply.status,
                    accompanyReplyLike.count(),
                    accompanyReply.createdAt,
                    QUserBasicInfoDto(
                        user.id,
                        user.email,
                        user.nickname,
                    )
                )
            )
            .from(accompanyReply)
            .leftJoin(accompanyReplyLike)
            .on(accompanyReply.id.eq(accompanyReplyLike.replyId))
            .innerJoin(user)
            .on(accompanyReply.userId.eq(user.id))
            .where(
                accompanyIdEq(accompanyId)
            )
            .groupBy(accompanyReply.id)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val totalCount = queryFactory
            .select(accompanyReply.count())
            .from(accompanyReply)
            .where(
                accompanyIdEq(accompanyId)
            )

        return PageableExecutionUtils.getPage(contents, pageable) { totalCount.fetchOne() ?: 0L }
    }

    private fun accompanyIdEq(accompanyId: Long): BooleanExpression? {
        return accompanyReply.accompanyId.eq(accompanyId)
    }
}
