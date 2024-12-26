package io.hhplus.cleanarchitecture.api.lecture.controller.dto.response

import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.AvailableLectureResult
import java.time.LocalDateTime

data class AvailableLectureResponse(
    val lectureId: Long,
    val title: String,
    val lecturer: String,
    val applyCount: Int,
    val maxCapacity: Int,
    val datetime: LocalDateTime,
) {
    companion object {
        fun from(
            availableLectureResult: AvailableLectureResult,
        ): AvailableLectureResponse {
            return AvailableLectureResponse(
                lectureId = availableLectureResult.lectureId,
                title = availableLectureResult.title,
                lecturer = availableLectureResult.lecturer,
                applyCount = availableLectureResult.applyCount,
                maxCapacity = availableLectureResult.maxCapacity,
                datetime = availableLectureResult.datetime,
            )
        }
    }
}
