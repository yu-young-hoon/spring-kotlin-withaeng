package com.withaeng.api.applicationservice.accompany

import com.withaeng.api.applicationservice.accompany.dto.*
import com.withaeng.api.applicationservice.common.PagingResponse
import com.withaeng.api.applicationservice.common.toPaging
import com.withaeng.api.common.PageInfoRequest
import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.accompanyjoinrequests.AccompanyJoinRequestService
import com.withaeng.domain.accompanylike.AccompanyLikeService
import org.springframework.stereotype.Service

@Service
class AccompanyApplicationService(
    private val accompanyService: AccompanyService,
    private val accompanyLikeService: AccompanyLikeService,
    private val accompanyJoinRequestService: AccompanyJoinRequestService,
) {

    fun create(request: CreateAccompanyServiceRequest): AccompanyResponse {
        return accompanyService.create(request.toDomainDto())
            .toAccompanyResponse(0L)
    }

    fun update(request: UpdateAccompanyServiceRequest): AccompanyResponse {
        val accompanyDto = accompanyService.update(request.toDomainDto())
        val likeCount = countAccompanyLikeByAccompanyId(request.accompanyId)
        return accompanyDto.toAccompanyResponse(likeCount)
    }

    fun search(
        pageInfoRequest: PageInfoRequest,
        request: SearchAccompanyServiceRequest,
    ): PagingResponse<List<AccompanySummaryResponse>> {
        val pageResult = accompanyService.search(pageInfoRequest.toPageRequest(), request.toQuery())
            .map { accompanyDto -> accompanyDto.toAccompanyResponse() }
        return PagingResponse(pageResult.content, pageResult.toPaging())
    }

    fun detail(accompanyId: Long, userId: Long?): FindAccompanyResponse {
        increaseViewCount(accompanyId)
        val accompanyDto = accompanyService.detail(accompanyId)
            .toAccompanyResponse()

        if (isHost(userId, accompanyDto.userId)) {
            // TODO : Host인 경우 승인보류 유저 목록 추가 필요
            println("호스트임")
        }

        return accompanyDto
    }

    fun retrieveAll(): List<AccompanyResponse> {
        val accompanyDtoList = accompanyService.findAll()
        // TODO: Bulk로 가져오는 방법을 고안
        return accompanyDtoList.map { accompanyDto ->
            accompanyDto.toAccompanyResponse(countAccompanyLikeByAccompanyId(accompanyDto.id))
        }
    }

    fun requestJoin(accompanyId: Long, userId: Long) {
        val accompanyDto = accompanyService.findById(accompanyId)
        validateSelfRequestNotAllowed(accompanyDto.userId, userId)
        accompanyJoinRequestService.create(accompanyId, userId)
    }

    fun cancelJoin(accompanyId: Long, userId: Long, joinRequestId: Long) {
        val accompanyDto = accompanyService.findById(accompanyId)
        val accompanyJoinRequestDto = accompanyJoinRequestService.findById(joinRequestId)
        validateSelfRequestNotAllowed(accompanyDto.userId, userId)
        validateCreatorAccess(accompanyJoinRequestDto.userId, userId)
        accompanyJoinRequestService.cancelJoin(accompanyId, joinRequestId)
    }

    fun acceptJoin(accompanyId: Long, userId: Long, joinRequestId: Long) {
        accompanyJoinRequestService.acceptJoin(accompanyId, joinRequestId)
    }

    fun rejectJoin(accompanyId: Long, userId: Long, joinRequestId: Long) {
        val accompanyDto = accompanyService.findById(accompanyId)
        validateCreatorAccess(accompanyDto.userId, userId)
        accompanyJoinRequestService.rejectJoin(accompanyId, joinRequestId)
    }

    private fun increaseViewCount(accompanyId: Long) {
        accompanyService.increaseViewCount(accompanyId)
    }

    private fun isHost(loginUserId: Long?, userId: Long) =
        loginUserId == userId

    private fun countAccompanyLikeByAccompanyId(accompanyId: Long): Long {
        return accompanyLikeService.countByAccompanyId(accompanyId)
    }

    private fun validateSelfRequestNotAllowed(createUserId: Long, requestUserId: Long) {
        if (createUserId == requestUserId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
                message = "본인의 동행은 신청, 취소할 수 없습니다."
            )
        }
    }

    private fun validateCreatorAccess(createUserId: Long, requestUserId: Long) {
        if (createUserId != requestUserId) {
            throw WithaengException.of(
                type = WithaengExceptionType.ACCESS_DENIED,
                message = "접근 권한이 없습니다."
            )
        }
    }
}