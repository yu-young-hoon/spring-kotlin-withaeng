package com.travel.withaeng.applicationservice.common

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

interface PagingResponse<T> {
    fun getPaging(): Paging
    fun getContent(): T
}