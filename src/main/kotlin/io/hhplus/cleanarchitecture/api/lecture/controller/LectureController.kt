package io.hhplus.cleanarchitecture.api.lecture.controller

import com.hhplus.board.support.response.ApiResponse
import io.hhplus.cleanarchitecture.api.lecture.controller.dto.response.ApplicationLectureResponse
import io.hhplus.cleanarchitecture.api.lecture.controller.dto.response.AvailableLectureResponse
import io.hhplus.cleanarchitecture.api.lecture.facade.LectureFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class LectureController(
    private val lectureFacade: LectureFacade,
) {

    /**
     * 특강 선택 API
     */
    @GetMapping("/api/lectures/available")
    fun getAvailableLectures(
        @RequestParam date: LocalDate,
    ): ApiResponse<List<AvailableLectureResponse>> {
        val result = lectureFacade.getAvailableLectures(date)
        return ApiResponse.success(
            result.map { AvailableLectureResponse.from(it) }
        )
    }

    /**
     * 특강 신청 완료 목록 조회 API
     */
    @GetMapping("/api/lectures/application")
    fun getUserLectures(
        @RequestParam userId: Long
    ): ApiResponse<List<ApplicationLectureResponse>> {
        val result = lectureFacade.getAppliedLectures(userId)
        return ApiResponse.success(
            result.map { ApplicationLectureResponse.from(it) }
        )
    }

}