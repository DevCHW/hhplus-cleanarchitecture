package com.example.ktboard.domain.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
    val logLevel: LogLevel,
) {
    // Lecture
    LECTURE_NOT_FOUND(NOT_FOUND, "존재하지 않는 특강입니다.", LogLevel.WARN),
    CAPACITY_EXCEEDED(CONFLICT, "신청한 특강의 정원이 초과되었습니다.", LogLevel.WARN),
    ALREADY_APPLIED(HttpStatus.BAD_REQUEST, "이미 신청한 특강입니다.", LogLevel.WARN),

    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다.", LogLevel.ERROR),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", LogLevel.WARN),
}