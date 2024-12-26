package io.hhplus.cleanarchitecture.api.lecture.facade.dto.result

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import java.time.LocalDateTime

data class AppliedLectureResult(
    val lectureId: Long,
    val title: String,
    val lecturer: String,
    val datetime: LocalDateTime,
) {
    companion object {
        fun from(lecture: Lecture): AppliedLectureResult {
            return AppliedLectureResult(
                lectureId = lecture.id,
                title = lecture.title,
                lecturer = lecture.lecturer,
                datetime = lecture.lectureDatetime,
            )
        }
    }
}
