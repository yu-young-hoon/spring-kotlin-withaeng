package com.withaeng.external.image

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration


@Component
class PreSignedUrlGenerator(
    private val presigner: S3Presigner,
    @Value("\${cloud.aws.s3.bucket}")
    private var bucket: String,
) {

    fun generate(objectKey: String): PreSignedUrl =
        PreSignedUrl(
            presigner.presignPutObject(
                preSignRequest(
                    Duration.ofMinutes(1), objectRequest(objectKey)
                )
            ).url().toString()
        )

    private fun preSignRequest(
        expiration: Duration,
        objectRequest: PutObjectRequest?,
    ): PutObjectPresignRequest? = PutObjectPresignRequest.builder()
        .signatureDuration(expiration)
        .putObjectRequest(objectRequest)
        .build()

    private fun objectRequest(objectKey: String): PutObjectRequest? {
        val objectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(objectKey)
            .build()
        return objectRequest
    }
}