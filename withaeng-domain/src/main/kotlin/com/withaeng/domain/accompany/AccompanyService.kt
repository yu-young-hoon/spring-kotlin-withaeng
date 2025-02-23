package com.withaeng.domain.accompany

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.accompany.dto.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

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
            title = params.title,
        )

        return accompany.toDto()
    }

    @Transactional(readOnly = true)
    fun search(pageable: Pageable, query: SearchAccompanyQuery): Page<SearchAccompanyDto> {
        return accompanyRepository.searchAccompanies(pageable, query)
    }

    @Transactional(readOnly = true)
    fun detail(accompanyId: Long): FindAccompanyDto {
        val findAccompanyDto = accompanyRepository.findAccompanyDetail(accompanyId)
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = NOT_EXIST_MESSAGE
            )
        if (findAccompanyDto.deletedAt != null) {
            throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = NOT_EXIST_MESSAGE
            )
        }
        return findAccompanyDto
    }

    @Transactional
    fun increaseViewCount(accompanyId: Long) {
        val accompany = accompanyRepository.findByIdAndDeletedAtIsNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        accompany.increaseViewCount()
    }

    @Transactional
    fun delete(accompanyId: Long) {
        val accompany = accompanyRepository.findByIdAndDeletedAtIsNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        accompany.deletedAt = LocalDateTime.now()
        accompanyRepository.save(accompany)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<AccompanyDto> {
        return accompanyRepository.findAllOrderByDesc().map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun random(): List<AccompanyDto> {
        return accompanyRepository.random().map { it.toDto() }
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

    @Transactional
    fun updateStatusToComplete(accompanyId: Long) {
        val accompany = accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )
        accompany.updateStatusToComplete()
    }
}