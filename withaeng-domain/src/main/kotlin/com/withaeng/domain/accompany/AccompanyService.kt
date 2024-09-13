package com.withaeng.domain.accompany

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.tag.TagRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccompanyService(
    private val accompanyRepository: AccompanyRepository,
    private val tagRepository: TagRepository,
) {

    companion object {
        private const val NOT_EXIST_MESSAGE = "해당하는 동행을 찾을 수 없습니다."
    }

    @Transactional
    fun create(params: CreateAccompanyDto): AccompanyDto {
        val actualTagIds = filterValidTagIds(params.tagIds)
        val actualParams = params.copy(tagIds = actualTagIds)
        val accompanyEntity = Accompany.create(actualParams)
        accompanyRepository.save(accompanyEntity)
        return accompanyEntity.toDto()
    }

    @Transactional
    fun update(params: UpdateAccompanyDto): AccompanyDto {
        val accompany = findById(params.accompanyId)
        accompany.update(
            content = params.content,
            tagIds = params.tagIds,
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
        val accompany = findById(accompanyId)
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

    private fun findById(accompanyId: Long) =
        accompanyRepository.findByIdOrNull(accompanyId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = NOT_EXIST_MESSAGE
        )

    private fun filterValidTagIds(tagIds: Set<Long>?): Set<Long> {
        if (tagIds == null) return emptySet()
        return tagRepository.findAllById(tagIds)
            .map { it.id }
            .toSet()
    }
}