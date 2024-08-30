package com.withaeng.api.common

import com.withaeng.api.applicationservice.common.Paging
import com.withaeng.api.applicationservice.common.PagingResponse
import com.withaeng.common.exception.WithaengExceptionType

data class ApiResponse<T>(
    val success: Boolean = true,
    val data: T? = null,
    val error: ApiErrorResponse? = null,
    val paging: Paging? = null
) {
    companion object {

        fun success(): ApiResponse<Unit> {
            return ApiResponse(success = true, data = null)
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(success = true, data = data)
        }

        fun <T> success(data: PagingResponse<T>): ApiResponse<T> {
            return ApiResponse(success = true, data = data.content, paging = data.paging)
        }

        fun fail(
            exceptionType: WithaengExceptionType,
            message: String?
        ): ApiResponse<Unit> {
            return ApiResponse(
                success = false,
                data = null,
                error = ApiErrorResponse(
                    code = exceptionType.errorCode,
                    message = message ?: exceptionType.message
                )
            )
        }
    }
}

data class ApiErrorResponse(val code: String, val message: String?)