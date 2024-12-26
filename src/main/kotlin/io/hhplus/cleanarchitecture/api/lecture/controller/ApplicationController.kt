package io.hhplus.cleanarchitecture.api.lecture.controller

import com.hhplus.board.support.response.ApiResponse
import io.hhplus.cleanarchitecture.api.lecture.controller.dto.request.CreateApplicationRequest
import io.hhplus.cleanarchitecture.api.lecture.controller.dto.response.CreateApplicationResponse
import io.hhplus.cleanarchitecture.api.lecture.facade.ApplicationFacade
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplicationController(
    private val applicationFacade: ApplicationFacade,
) {

    /**
     * 특강 신청 API
     */
    @PostMapping("/api/lectures/{lectureId}/application")
    fun applyLecture(
        @PathVariable lectureId: Long,
        @RequestBody request: CreateApplicationRequest,
    ): ApiResponse<CreateApplicationResponse> {
        val result = applicationFacade.apply(request.userId, lectureId)
        return ApiResponse.success(CreateApplicationResponse.from(result))
    }
}