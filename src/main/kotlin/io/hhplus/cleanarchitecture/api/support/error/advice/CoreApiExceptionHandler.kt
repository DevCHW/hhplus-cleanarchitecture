package io.hhplus.cleanarchitecture.api.support.error.advice

import com.example.ktboard.domain.error.CoreApiException
import com.example.ktboard.domain.error.ErrorCode
import com.hhplus.board.support.response.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(1)
class CoreApiExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CoreApiException::class)
    fun handleCoreException(e: CoreApiException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorCode.logLevel) {
            LogLevel.DEBUG -> log.debug("{} : {}", e.errorCode, e.errorMessage)
            LogLevel.INFO -> log.info("{} : {}", e.errorCode, e.errorMessage)
            LogLevel.ERROR -> log.error("{} : {}", e.errorCode, e.errorMessage, e)
            LogLevel.WARN -> log.warn("{} : {}", e.message, e.errorMessage)
            else -> log.info("CoreException : {}", e.message)
        }
        return ResponseEntity(ApiResponse.error(e.errorCode), e.errorCode.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorCode.SERVER_ERROR), ErrorCode.SERVER_ERROR.status)
    }

}