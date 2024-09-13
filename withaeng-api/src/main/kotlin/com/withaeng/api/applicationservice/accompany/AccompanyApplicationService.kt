package com.withaeng.api.applicationservice.accompany

import com.withaeng.api.applicationservice.accompany.dto.*
import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.accompanylike.AccompanyLikeService
import org.springframework.stereotype.Service

@Service
class AccompanyApplicationService(
    private val accompanyService: AccompanyService,
    private val accompanyLikeService: AccompanyLikeService,
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

    private fun increaseViewCount(accompanyId: Long) {
        accompanyService.increaseViewCount(accompanyId)
    }

    private fun isHost(loginUserId: Long?, userId: Long) =
        loginUserId == userId

    fun retrieveAll(): List<AccompanyResponse> {
        val accompanyDtoList = accompanyService.findAll()
        // TODO: Bulk로 가져오는 방법을 고안
        return accompanyDtoList.map { accompanyDto ->
            accompanyDto.toAccompanyResponse(countAccompanyLikeByAccompanyId(accompanyDto.id))
        }
    }

    private fun countAccompanyLikeByAccompanyId(accompanyId: Long): Long {
        return accompanyLikeService.countByAccompanyId(accompanyId)
    }
}