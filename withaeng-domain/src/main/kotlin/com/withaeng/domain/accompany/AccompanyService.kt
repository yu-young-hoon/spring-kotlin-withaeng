package com.withaeng.domain.accompany

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccompanyService(
    private val accompanyRepository: AccompanyRepository,
) {

    companion object {
        private const val NOT_EXIST_MESSAGE = "해당하는 동행을 찾을 수 없습니다."
    }

    @Transactional
    fun create(params: CreateAccompanyDto): AccompanyDto {
        val accompanyEntity = Accompany.create(params)
        accompanyRepository.save(accompanyEntity)
        return accompanyEntity.toDto()
    }

    @Transactional
    fun update(params: UpdateAccompanyDto): AccompanyDto {
        val accompany = accompanyRepository.findByIdOrNull(params.accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        accompany.update(
            content = params.content,
            tags = params.tags,
        )

        return accompany.toDto()
    }

    @Transactional(readOnly = true)
    fun detail(accompanyId: Long): FindAccompanyDto {
        return accompanyRepository.findAccompanyDetail(accompanyId)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = NOT_EXIST_MESSAGE
            )
    }

    @Transactional
    fun increaseViewCount(accompanyId: Long) {
        val accompany = accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        accompany.increaseViewCount()
    }

    @Transactional(readOnly = true)
    fun findAll(): List<AccompanyDto> {
        return accompanyRepository.findAll().map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun countByUserId(userId: Long): Int {
        return accompanyRepository.countByUserId(userId)
    }

    @Transactional(readOnly = true)
    fun findById(accompanyId: Long): AccompanyDto {
        val accompany = accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        return accompany.toDto()
    }
}