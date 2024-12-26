package io.hhplus.cleanarchitecture.api.lecture.controller.dto.response

import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.AppliedLectureResult
import java.time.LocalDateTime

data class ApplicationLectureResponse(
    val lectureId: Long,
    val title: String,
    val lecturer: String,
    val datetime: LocalDateTime,
) {
    companion object {
        fun from(appliedLectureResult: AppliedLectureResult): ApplicationLectureResponse {
            return ApplicationLectureResponse(
                lectureId = appliedLectureResult.lectureId,
                title = appliedLectureResult.title,
                lecturer = appliedLectureResult.lecturer,
                datetime = appliedLectureResult.datetime,
            )
        }
    }
}
