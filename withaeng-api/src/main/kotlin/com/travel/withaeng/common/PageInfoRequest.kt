package com.travel.withaeng.common

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.PageRequest

@Schema(description = "[Request] 페이지 정보")
data class PageInfoRequest(
    @Schema(description = "요청할 페이지 번호 (0부터 시작)")
    private val page: String = "0",
    @Schema(description = "페이지당 데이터 개수")
    private val size: String = "5",
) {

    private val pageAsInt = initPage(getIntegerValue(page))
    private val sizeAsInt = initSize(getIntegerValue(size))

    fun toPageRequest() = PageRequest.of(pageAsInt, sizeAsInt)

    private fun getIntegerValue(value: String?) = value?.toIntOrNull()

    private fun initPage(page: Int?): Int {
        val defaultPage = 0
        val minimumPage = 0
        return page?.takeIf { it >= minimumPage } ?: defaultPage
    }

    private fun initSize(size: Int?): Int {
        val defaultSize = 5
        val minimumSize = 1
        val maxSize = 20
        return size?.takeIf { it in minimumSize..maxSize } ?: defaultSize
    }

}