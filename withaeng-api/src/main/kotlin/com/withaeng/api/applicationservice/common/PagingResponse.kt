package com.withaeng.api.applicationservice.common

import org.springframework.data.domain.Page

data class Paging(
    val totalCount: Long,
    val page: Int,
    val offset: Long
)

fun Page<*>.toPaging(): Paging = Paging(
    totalCount = totalElements,
    page = pageable.pageNumber,
    offset = pageable.offset
)

class PagingResponse<T>(
    val content: T,
    val paging: Paging
)