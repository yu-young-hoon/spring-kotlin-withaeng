package com.withaeng.domain.verificationemail

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class VerificationEmailService(
    private val userRepository: UserRepository,
    private val verificationEmailRepository: VerificationEmailRepository,
) {

    @Transactional
    fun create(email: String, userId: Long, code: String, type: VerificationEmailType): VerificationEmailDto {
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
        return verificationEmailRepository.save(
            VerificationEmail(
                email = email,
                userId = userId,
                code = code,
                type = type
            )
        ).toDto()
    }

    fun findByEmail(email: String): VerificationEmailDto {
        return verificationEmailRepository.findByEmail(email)?.toDto()
            ?: throw WithaengException.of(
                type = WithaengExceptionType.NOT_EXIST,
                message = "이메일에 해당하는 요청이 없습니다."
            )
    }

    fun findAllByStatusNot(status: VerificationEmailStatus): List<VerificationEmailDto> {
        return verificationEmailRepository.findAllByStatusNot(status).map { it.toDto() }
    }

    @Transactional
    fun deleteById(id: Long) {
        verificationEmailRepository.deleteById(id)
    }

    @Transactional
    fun deleteAllById(ids: Set<Long>) {
        verificationEmailRepository.deleteAllById(ids)
    }

    @Transactional
    fun deleteAllByUserId(userId: Long) {
        verificationEmailRepository.deleteAllByUserId(userId)
    }

    @Transactional
    fun deleteAllByUserIdAndEmailType(userId: Long, emailType: VerificationEmailType) {
        verificationEmailRepository.deleteAllByUserIdAndType(userId, emailType)
    }

    @Transactional
    fun updateStatusByIds(ids: Set<Long>, status: VerificationEmailStatus): Int {
        return verificationEmailRepository.updateStatusByIds(ids, status)
    }
}
