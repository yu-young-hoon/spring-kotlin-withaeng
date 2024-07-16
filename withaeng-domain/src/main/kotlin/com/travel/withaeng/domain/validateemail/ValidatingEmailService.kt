package com.travel.withaeng.domain.validateemail

import com.travel.withaeng.common.exception.WithaengException
import com.travel.withaeng.common.exception.WithaengExceptionType
import com.travel.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ValidatingEmailService(
    private val userRepository: UserRepository,
    private val validatingEmailRepository: ValidatingEmailRepository
) {

    @Transactional
    fun create(email: String, userId: Long, code: String, type: ValidatingEmailType): ValidatingEmailDto {
        userRepository.findByIdOrNull(userId) ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "$userId 에 해당하는 사용자를 찾을 수 없습니다."
        )
        if (email.isBlank() || code.isBlank()) {
            throw WithaengException.of(
                type = WithaengExceptionType.ARGUMENT_NOT_VALID,
                message = "이메일 인증을 진행하는데 올바르지 않은 입력입니다."
            )
        }
        return validatingEmailRepository.save(
            ValidatingEmail(
                email = email,
                userId = userId,
                code = code,
                type = type
            )
        ).toDto()
    }

    fun findByEmail(email: String): ValidatingEmailDto {
        return validatingEmailRepository.findByEmail(email)?.toDto()
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "이메일에 해당하는 요청이 없습니다."
            )
    }

    fun findAllByStatusNot(status: ValidatingEmailStatus): List<ValidatingEmailDto> {
        return validatingEmailRepository.findAllByStatusNot(status).map { it.toDto() }
    }

    @Transactional
    fun deleteById(id: Long) {
        validatingEmailRepository.deleteById(id)
    }

    @Transactional
    fun deleteAllById(ids: Set<Long>) {
        validatingEmailRepository.deleteAllById(ids)
    }

    @Transactional
    fun deleteAllByUserId(userId: Long) {
        validatingEmailRepository.deleteAllByUserId(userId)
    }

    @Transactional
    fun deleteAllByUserIdAndEmailType(userId: Long, emailType: ValidatingEmailType) {
        validatingEmailRepository.deleteAllByUserIdAndType(userId, emailType)
    }

    @Transactional
    fun updateStatusByIds(ids: Set<Long>, status: ValidatingEmailStatus): Int {
        return validatingEmailRepository.updateStatusByIds(ids, status)
    }
}
