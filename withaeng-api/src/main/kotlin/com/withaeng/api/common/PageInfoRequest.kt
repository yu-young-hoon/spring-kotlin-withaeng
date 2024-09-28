package com.withaeng.api.common

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.PageRequest

@Schema(description = "[Request] 페이지 정보")
data class PageInfoRequest(
    @Schema(description = "요청할 페이지 번호 (0부터 시작)")
    private val page: Int = 0,
    @Schema(description = "페이지당 데이터 개수")
    private val size: Int = 8,
) {

    fun toPageRequest() = PageRequest.of(page, size)
}