package com.travel.withaeng.domain.accompanyreply

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.accompany.AccompanyRepository
import com.travel.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyReplyService(
    private val userRepository: UserRepository,
    private val accompanyRepository: AccompanyRepository,
    private val accompanyReplyRepository: AccompanyReplyRepository
) {

    @Transactional
    fun create(accompanyId: Long, userId: Long, content: String, parentId: Long? = null): AccompanyReplyDto {
        userRepository.findByIdOrNull(userId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 유저를 찾을 수 없습니다."
        )
        accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 동행을 찾을 수 없습니다."
        )
        if (parentId != null) {
            accompanyReplyRepository.findByIdOrNull(parentId) ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "해당하는 댓글을 찾을 수 없습니다."
            )
        }
        val accompanyReply = AccompanyReply.create(
            accompanyId = accompanyId,
            userId = userId,
            content = content,
            parentId = parentId
        )
        accompanyReplyRepository.save(accompanyReply)
        return accompanyReply.toDto()
    }

    fun findById(replyId: Long): AccompanyReplyDto {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 댓글을 찾을 수 없습니다."
        )
        return accompanyReply.toDto()
    }

    fun findAllByAccompanyId(accompanyId: Long): List<AccompanyReplyDto> {
        return accompanyReplyRepository.findAllByAccompanyId(accompanyId)
            .map { it.toDto() }
    }

    @Transactional
    fun update(replyId: Long, content: String): AccompanyReplyDto {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 댓글을 찾을 수 없습니다."
        )
        accompanyReply.content = content
        return accompanyReply.toDto()
    }

    @Transactional
    fun delete(replyId: Long) {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 댓글을 찾을 수 없습니다."
        )
        accompanyReplyRepository.delete(accompanyReply)
    }
}