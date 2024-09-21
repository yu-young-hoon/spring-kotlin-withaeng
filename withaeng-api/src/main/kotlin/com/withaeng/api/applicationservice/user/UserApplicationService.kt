package com.withaeng.api.applicationservice.user

import com.withaeng.api.applicationservice.user.dto.*
import com.withaeng.domain.accompany.AccompanyService
import com.withaeng.domain.user.UserService
import com.withaeng.external.image.PreSignedUrl
import com.withaeng.external.image.PreSignedUrlGenerator
import com.withaeng.external.image.S3StorageClient
import org.springframework.stereotype.Service
import java.util.*

private const val USER_IMAGE_STORAGE_DIR = "user"

@Service
class UserApplicationService(
    private val userService: UserService,
    private val accompanyService: AccompanyService,
    private val preSignedUrlGenerator: PreSignedUrlGenerator,
    private val s3StorageClient: S3StorageClient,
) {

    fun getProfile(userId: Long): UserStatisticalProfileResponse {
        val userDetail = userService.findDetailById(userId)
        val profileCompletionPercentage = userService.getProfileCompletionPercentage(userId)
        val accompanyCount = accompanyService.countByUserId(userId)
        return UserStatisticalProfileResponse.of(
            userDetail = userDetail,
            profileCompletionPercentage = profileCompletionPercentage,
            accompanyCount = accompanyCount,
        )
    }

    fun getTravelPreference(userId: Long): UserTravelPreferenceResponse {
        return userService.findDetailById(userId).travelPreference?.toServiceResponse()
            ?: UserTravelPreferenceResponse()
    }

    fun updateProfile(userId: Long, serviceRequest: UpdateProfileServiceRequest): UpdateProfileResponse {
        if (serviceRequest.hasImage) {
            val preSignedUrl = generatePreSignedUrl()
            val userSimpleDto = userService.updateProfile(userId, serviceRequest.toCommand(preSignedUrl.imageUrl()))
            return UpdateProfileResponse(userSimpleDto.id, preSignedUrl.uploadUrl())
        }
        val userSimpleDto = userService.updateProfile(userId, serviceRequest.toCommand())
        return UpdateProfileResponse(userSimpleDto.id)
    }

    private fun generatePreSignedUrl(): PreSignedUrl {
        val objectKey = "$USER_IMAGE_STORAGE_DIR/${UUID.randomUUID()}"
        return preSignedUrlGenerator.generate(objectKey)
    }

    fun updateTravelPreference(userId: Long, serviceRequest: UpdateTravelPreferenceServiceRequest): UserDetailResponse {
        return userService.replaceTravelPreference(userId, serviceRequest.toCommand()).toDetailResponse()
    }

    fun deleteProfileImage(id: Long) {
        val userSimpleDto = userService.findSimpleById(id)
        userSimpleDto.profile.profileImageUrl?.let {
            userService.deleteProfileImage(id)
            deleteS3Image(it)
        }
    }

    private fun deleteS3Image(it: String) {
        val objectKey = "$USER_IMAGE_STORAGE_DIR/${it.split("/").last()}"
        s3StorageClient.delete(objectKey)
    }
}
