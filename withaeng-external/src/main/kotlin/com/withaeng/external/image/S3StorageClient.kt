package com.withaeng.external.image

interface S3StorageClient {

    fun delete(objectKey: String)
}