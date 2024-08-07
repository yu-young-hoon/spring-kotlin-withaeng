package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Table(name = "accompany_reply")
@Entity
class AccompanyReply(

    @Column(name = "accompany_id", nullable = false)
    val accompanyId: Long,

    @Column(name = "parent_id", nullable = true)
    val parentId: Long? = null,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "content", nullable = false)
    var content: String

) : BaseEntity() {
    companion object {
        fun create(accompanyId: Long, userId: Long, content: String, parentId: Long? = null): AccompanyReply {
            return AccompanyReply(
                accompanyId = accompanyId,
                userId = userId,
                content = content,
                parentId = parentId
            )
        }
    }

    fun updateAccompanyReply(
        newContent: String
    ) {
        this.content = newContent
    }
}