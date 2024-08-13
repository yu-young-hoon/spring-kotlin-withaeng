package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

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
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: AccompanyReplyStatus,

    ) : BaseEntity() {
    companion object {
        fun create(accompanyId: Long, userId: Long, content: String, parentId: Long? = null): AccompanyReply {
            return AccompanyReply(
                accompanyId = accompanyId,
                userId = userId,
                content = content,
                parentId = parentId,
                status = AccompanyReplyStatus.ACTIVE
            )
        }
    }

    fun update(
        newContent: String
    ) {
        this.content = newContent
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
        this.status = AccompanyReplyStatus.DELETED
    }

}