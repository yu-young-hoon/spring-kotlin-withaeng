package com.travel.withaeng.domain.accompanylike

import com.travel.withaeng.common.cd.ExecCd
import org.jetbrains.annotations.NotNull

class CreateAccompanyLikeDTO(

    @NotNull
    val userId: Long,

    @NotNull
    val accompanyId: Long,

    ) {
    fun toEntity(): AccompanyLikeEntity {
        return AccompanyLikeEntity(
            0,
            this.userId,
            this.accompanyId
        )
    }

    fun toHistEntity(entity: AccompanyLikeEntity): AccompanyLikeHistEntity {
        return AccompanyLikeHistEntity(
            0,
            entity.likeId,
            entity.userId,
            entity.accompanyId,
            ExecCd.CREATE.execCd
        )
    }

}

class DeleteAccompanyLikeDTO(

    @NotNull
    val likeId: Long,

    @NotNull
    val userId: Long,

    @NotNull
    val accompanyId: Long,

    ) {
    fun toEntity(): AccompanyLikeEntity {
        return AccompanyLikeEntity(
            this.likeId,
            this.userId,
            this.accompanyId
        )
    }

    fun toHistEntity(entity: AccompanyLikeEntity): AccompanyLikeHistEntity {
        return AccompanyLikeHistEntity(
            0,
            entity.likeId,
            entity.userId,
            entity.accompanyId,
            ExecCd.DELETE.execCd
        )
    }

}