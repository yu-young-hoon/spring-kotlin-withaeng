package com.withaeng.external.image

class PreSignedUrl(
    private val url: String,
) {
    fun uploadUrl(): String {
        return url
    }

    fun imageUrl(): String {
        return url.substring(0, url.indexOf("?"))
    }
}
