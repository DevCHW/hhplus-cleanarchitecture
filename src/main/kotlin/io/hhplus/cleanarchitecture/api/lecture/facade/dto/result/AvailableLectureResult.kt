package io.hhplus.cleanarchitecture.api.lecture.facade.dto.result

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import java.time.LocalDateTime

data class AvailableLectureResult(
    val lectureId: Long,
    val title: String,
    val lecturer: String,
    val applyCount: Int,
    val maxCapacity: Int,
    val datetime: LocalDateTime,
) {
    companion object {
        fun from(lecture: Lecture): AvailableLectureResult {
            return AvailableLectureResult(
                lectureId = lecture.id,
                title = lecture.title,
                lecturer = lecture.lecturer,
                applyCount = lecture.applicationCount,
                maxCapacity = lecture.maxCapacity,
                datetime = lecture.lectureDatetime,
            )
        }
    }
}
