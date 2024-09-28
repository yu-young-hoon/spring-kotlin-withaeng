package com.withaeng.external.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.ses.SesClient

@Configuration
class SesConfig(
    @Value("\${cloud.aws.credentials.access-key}")
    private var accessKey: String,
    @Value("\${cloud.aws.credentials.secret-key}")
    private var secretKey: String,
) {

    @Bean
    fun sesClient(): SesClient {
        return SesClient.builder()
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .region(Region.AP_NORTHEAST_2)
            .build()
    }
}