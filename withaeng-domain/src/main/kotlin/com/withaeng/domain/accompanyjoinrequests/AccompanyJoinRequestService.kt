package com.withaeng.domain.accompanyjoinrequests

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.Accompany
import com.withaeng.domain.accompany.AccompanyRepository
import com.withaeng.domain.accompanyjoinrequests.dto.AccompanyJoinRequestDto
import com.withaeng.domain.accompanyjoinrequests.dto.toDto
import com.withaeng.domain.accompanyrequests.AccompanyJoinRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccompanyJoinRequestService(
    private val accompanyJoinRequestRepository: AccompanyJoinRequestRepository,
    private val accompanyRepository: AccompanyRepository,
) {

    @Transactional
    fun create(accompanyId: Long, userId: Long) {
        val accompany = findAccompanyByIdOrNull(accompanyId)
        validateFull(accompany, accompanyId)
        validateDuplicateJoin(accompanyId, userId)
        val accompanyJoinRequest = AccompanyJoinRequest.create(userId, accompany)
        accompanyJoinRequestRepository.save(accompanyJoinRequest)
    }

    @Transactional
    fun cancelJoin(accompanyId: Long, joinRequestId: Long) {
        val accompany = findAccompanyByIdOrNull(accompanyId)
        validateCompletedAccompany(accompany)
        val accompanyJoinRequest = findAccompanyJoinRequestByIdOrNull(joinRequestId)
        accompanyJoinRequest.cancel()
    }

    @Transactional
    fun acceptJoin(accompanyId: Long, joinRequestId: Long) {
        val accompanyJoinRequest = findAccompanyJoinRequestByIdOrNull(joinRequestId)
        val accompany = findAccompanyByIdOrNull(accompanyId)
        validateFull(accompany, accompanyId)
        accompanyJoinRequest.accept()

        if (isBelowMemberLimit(accompany, accompanyId)) {
            accompany.updateStatusToComplete()
        }
    }

    @Transactional
    fun rejectJoin(accompanyId: Long, joinRequestId: Long) {
        val accompanyJoinRequest = findAccompanyJoinRequestByIdOrNull(joinRequestId)
        val accompany = findAccompanyByIdOrNull(accompanyId)
        validateFull(accompany, accompanyId)
        validateNotWaitingJoinRequest(accompanyJoinRequest)
        accompanyJoinRequest.reject()
    }

    @Transactional(readOnly = true)
    fun findById(joinRequestId: Long): AccompanyJoinRequestDto {
        val accompanyJoinRequest = findAccompanyJoinRequestByIdOrNull(joinRequestId)
        return accompanyJoinRequest.toDto()
    }

    private fun findAccompanyByIdOrNull(accompanyId: Long) =
        accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 동행을 찾을 수 없습니다."
        )

    private fun findAccompanyJoinRequestByIdOrNull(joinRequestId: Long) =
        accompanyJoinRequestRepository.findByIdOrNull(joinRequestId)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "동행 신청 정보가 존재하지 않습니다."
            )

    private fun getAcceptedJoinRequestCount(accompanyId: Long) =
        accompanyJoinRequestRepository.countByAccompanyIdAndStatus(accompanyId)

    private fun existsJoinRequestByUser(accompanyId: Long, userId: Long) =
        accompanyJoinRequestRepository.existsByAccompanyIdAndUserId(accompanyId, userId)

    private fun validateNotWaitingJoinRequest(accompanyJoinRequest: AccompanyJoinRequest) {
        if (accompanyJoinRequest.isNotWaiting()) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "승인 대기 상태가 아닙니다."
            )
        }
    }

    private fun validateCompletedAccompany(accompany: Accompany) {
        if (accompany.isCompleted()) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "모집 마감된 동행은 취소할 수 없습니다."
            )
        }
    }

    private fun validateFull(accompany: Accompany, accompanyId: Long) {
        if (isFull(accompany, accompanyId)) {
            throw WithaengException.of(
                type = WithaengExceptionType.INVALID_ACCESS,
                message = "모집 마감되었습니다."
            )
        }
    }

    private fun validateDuplicateJoin(accompanyId: Long, userId: Long) {
        if (existsJoinRequestByUser(accompanyId, userId)) {
            throw WithaengException.of(
                type = WithaengExceptionType.ALREADY_EXIST,
                message = "동행 신청한 내역이 존재합니다."
            )
        }
    }

    private fun isFull(accompany: Accompany, accompanyId: Long) =
        accompany.isCompleted() || isBelowMemberLimit(accompany, accompanyId)

    private fun isBelowMemberLimit(accompany: Accompany, accompanyId: Long) =
        accompany.memberCount < getAcceptedJoinRequestCount(accompanyId) + 1
}