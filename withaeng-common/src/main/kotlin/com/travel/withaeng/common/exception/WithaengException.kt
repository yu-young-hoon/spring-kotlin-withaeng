package com.travel.withaeng.common.exception

class WithaengException(
    val errorCode: String,
    val httpStatusCode: Int,
    override val message: String,
) : RuntimeException() {
    companion object {
        fun of(type: WithaengExceptionType): WithaengException {
            return WithaengException(
                errorCode = type.errorCode,
                httpStatusCode = type.httpStatusCode,
                message = type.message,
            )
        }

        fun of(type: WithaengExceptionType, message: String): WithaengException {
            return WithaengException(
                errorCode = type.errorCode,
                httpStatusCode = type.httpStatusCode,
                message = message,
            )
        }
    }
}
