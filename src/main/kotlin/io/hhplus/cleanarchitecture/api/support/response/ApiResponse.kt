package com.hhplus.board.support.response

import com.example.ktboard.domain.error.ErrorCode
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T> private constructor(
    val result: ResultType,
    val data: T? = null,
    val error: Error? = null,
) {
    companion object {
        fun success(): ApiResponse<Any> {
            return ApiResponse(ResultType.SUCCESS, null)
        }

        fun <S> success(data: S): ApiResponse<S> {
            return ApiResponse(ResultType.SUCCESS, data)
        }

        fun <S> error(errorCode: ErrorCode): ApiResponse<S> {
            return ApiResponse(ResultType.ERROR, error = Error(errorCode.name, errorCode.message))
        }

        fun <S> error(errorCode: ErrorCode, message: String): ApiResponse<S> {
            return ApiResponse(ResultType.ERROR, error = Error(errorCode.name, message))
        }

    }

    data class Error(
        val code: String,
        val message: String,
    )
}