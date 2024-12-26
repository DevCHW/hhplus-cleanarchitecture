package com.example.ktboard.domain.error

class CoreApiException(
    val errorCode: ErrorCode,
    val errorMessage: String? = null,
) : RuntimeException(errorMessage?: errorCode.message)
