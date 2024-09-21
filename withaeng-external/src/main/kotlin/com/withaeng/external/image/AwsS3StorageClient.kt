package com.withaeng.external.image

import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.s3.S3Client

@Component
class AwsS3StorageClient(
    private val s3Client: S3Client,
    @Value("\${cloud.aws.s3.bucket}")
    private var bucket: String,
) : S3StorageClient {

    @Async
    override fun delete(objectKey: String) {
        s3Client.deleteObject {
            it.bucket(bucket)
            it.key(objectKey)
        }
    }
}