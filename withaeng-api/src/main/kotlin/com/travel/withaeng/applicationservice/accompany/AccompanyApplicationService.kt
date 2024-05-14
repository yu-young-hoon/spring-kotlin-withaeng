package com.travel.withaeng.applicationservice.accompany

import com.travel.withaeng.applicationservice.accompany.dto.*
import com.travel.withaeng.domain.accompany.AccompanyService
import com.travel.withaeng.domain.accompanylike.AccompanyLikeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyApplicationService(
    private val accompanyService: AccompanyService,
    private val accompanyLikeService: AccompanyLikeService
) {

    @Transactional
    fun create(request: CreateAccompanyServiceRequest): AccompanyResponse {
        return accompanyService.create(request.toDomainDto()).toAccompanyResponse(0L)
    }

    @Transactional
    fun update(request: UpdateAccompanyServiceRequest): AccompanyResponse {
        val accompanyDto = accompanyService.update(request.toDomainDto())
        val likeCount = countAccompanyLikeByAccompanyId(request.accompanyId)
        return accompanyDto.toAccompanyResponse(likeCount)
    }

    fun retrieve(accompanyId: Long): AccompanyResponse {
        val accompanyDto = accompanyService.findById(accompanyId)
        val likeCount = countAccompanyLikeByAccompanyId(accompanyId)
        return accompanyDto.toAccompanyResponse(likeCount)
    }

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