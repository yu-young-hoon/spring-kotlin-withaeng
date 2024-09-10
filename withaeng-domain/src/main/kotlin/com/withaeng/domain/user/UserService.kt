package com.withaeng.domain.user

import com.withaeng.common.exception.WithaengException
import com.withaeng.common.exception.WithaengExceptionType
import com.withaeng.domain.user.dto.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.reflect.full.declaredMemberProperties

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun findSimpleById(id: Long): UserSimpleDto {
        return userRepository.findByIdOrNull(id).getOrThrow().toSimpleDto()
    }

    @Transactional(readOnly = true)
    fun findDetailById(id: Long): UserDetailDto {
        return userRepository.findByIdOrNull(id).getOrThrow().toDetailDto()
    }

    @Transactional(readOnly = true)
    fun findByEmailOrNull(email: String): UserSimpleDto? {
        return userRepository.findByEmail(email)?.toSimpleDto()
    }

    @Transactional(readOnly = true)
    fun getProfileCompletionPercentage(userId: Long): Int {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        val profile = user.profile
        val travelPreference = user.travelPreference

        val profileFields = UserProfile::class.declaredMemberProperties.filter { it.name != "user" }
        val travelPreferenceFields = UserTravelPreference::class.declaredMemberProperties.filter { it.name != "user" }

        val totalFields = profileFields.size + travelPreferenceFields.size
        var filledFields = 0

        profileFields.forEach { field ->
            if (field.get(profile) != null && field.get(profile) != "") {
                filledFields++
            }
        }

        travelPreferenceFields.forEach { field ->
            if (travelPreference != null && field.get(travelPreference) != null
                && field.get(travelPreference) != emptySet<Any>()
            ) {
                filledFields++
            }
        }

        return ((filledFields.toDouble() / totalFields) * 100).toInt()
    }

    @Transactional
    fun create(createUserCommand: CreateUserCommand): UserSimpleDto {
        return userRepository.save(
            User.create(
                email = createUserCommand.email,
                nickname = createUserCommand.nickname,
                password = createUserCommand.password,
                birth = createUserCommand.birth,
                gender = createUserCommand.gender
            )
        ).toSimpleDto()
    }

    @Transactional
    fun updateProfile(userId: Long, command: UpdateProfileCommand): UserSimpleDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        user.profile.nickname = command.nickname ?: user.profile.nickname
        user.profile.introduction = command.introduction
        user.profile.profileImageUrl = command.profileImageUrl
        return user.toSimpleDto()
    }

    @Transactional
    fun updateTravelPreference(userId: Long, command: UpdateTravelPreferenceCommand): UserDetailDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        user.travelPreference = user.travelPreference ?: UserTravelPreference.create(user)
        user.travelPreference?.mbti = command.mbti ?: emptySet()
        user.travelPreference?.preferTravelType = command.preferTravelType
        user.travelPreference?.preferTravelThemes = command.preferTravelThemes ?: emptySet()
        user.travelPreference?.consumeStyle = command.consumeStyle
        user.travelPreference?.foodRestrictions = command.foodRestrictions ?: emptySet()
        user.travelPreference?.smokingType = command.smokingType
        user.travelPreference?.drinkingType = command.drinkingType
        return user.toDetailDto()
    }

    @Transactional
    fun updatePassword(userId: Long, password: String): UserSimpleDto {
        val user = userRepository.findByIdOrNull(userId).getOrThrow()
        user.password = password
        return user.toSimpleDto()
    }

    @Transactional
    fun grantUserRole(id: Long) {
        val user = userRepository.findByIdOrNull(id)
            ?: throw WithaengException.of(WithaengExceptionType.SYSTEM_FAIL)
        val newUserRoles = user.roles.filter { it != UserRole.NON_USER } + listOf(UserRole.USER)
        user.roles = newUserRoles.toSet()
    }

    @Transactional
    fun deleteByEmail(email: String) {
        return userRepository.deleteByEmail(email)
    }

    private fun User?.getOrThrow(): User {
        this ?: throw WithaengException.of(
            type = WithaengExceptionType.NOT_EXIST,
            message = "해당하는 유저를 찾을 수 없습니다."
        )
        return this
    }
}
