package com.withaeng.domain.accompanyreply

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.AccompanyRepository
import com.withaeng.domain.user.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
        validateCreateAccompanyReply(accompanyId, userId)
        if (parentId != null) {
            validateNotExistsParentId(parentId)
        }
        val accompanyReply = AccompanyReply.create(
            accompanyId = accompanyId, userId = userId, content = content, parentId = parentId
        )
        accompanyReplyRepository.save(accompanyReply)
        return accompanyReply.toDto()
    }

    fun findById(replyId: Long): AccompanyReplyDto {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST, message = "해당하는 댓글을 찾을 수 없습니다."
        )
        return accompanyReply.toDto()
    }

    fun getList(accompanyId: Long, pageable: Pageable): Page<FindAccompanyReplyDto> {
        return accompanyReplyRepository.findAccompanyReplyList(accompanyId, pageable).map {
            it.takeIf { it.status == AccompanyReplyStatus.DELETED }?.copy(
                content = null,
                createdAt = null,
            ) ?: it
        }
    }

    @Transactional
    fun update(replyId: Long, content: String): AccompanyReplyDto {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST, message = "해당하는 댓글을 찾을 수 없습니다."
        )
        accompanyReply.update(content)
        return accompanyReply.toDto()
    }

    @Transactional
    fun delete(replyId: Long) {
        val accompanyReply = accompanyReplyRepository.findByIdOrNull(replyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST, message = "해당하는 댓글을 찾을 수 없습니다."
        )
        accompanyReply.delete()
    }

    private fun validateCreateAccompanyReply(accompanyId: Long, userId: Long) {
        validateExistsUser(userId)
        validateExistsAccompany(accompanyId)
    }

    private fun validateExistsAccompany(accompanyId: Long) {
        if (!accompanyRepository.existsById(accompanyId)) {
            throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST, message = "해당하는 동행을 찾을 수 없습니다."
            )
        }
    }

    private fun validateExistsUser(userId: Long) {
        if (!userRepository.existsById(userId)) {
            throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST, message = "해당하는 유저를 찾을 수 없습니다."
            )
        }
    }

    private fun validateNotExistsParentId(parentId: Long) {
        if (accompanyReplyRepository.existsById(parentId)) {
            throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST, message = "해당하는 댓글을 찾을 수 없습니다."
            )
        }
    }

}