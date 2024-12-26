package io.hhplus.cleanarchitecture.support.fixture

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import io.hhplus.cleanarchitecture.domain.lecture.model.LectureApplication
import java.time.LocalDateTime

class TestFixtureUtils {
    companion object {
        fun lecture(
            id: Long = 0L,
            title: String = "특강1",
            maxCapacity: Int = 30,
            lecturer: String = "강연자1",
            lectureDatetime: LocalDateTime = LocalDateTime.now(),
            applyCount: Int = 0,
        ): Lecture {
            return Lecture(
                id = id,
                title = title,
                maxCapacity = maxCapacity,
                lecturer = lecturer,
                lectureDatetime = lectureDatetime,
                applicationCount = applyCount,
            )
        }

        fun lectureApplication(
            id: Long = 0L,
            userId: Long = 1L,
            lectureId: Long = 1L,
        ): LectureApplication {
            return LectureApplication(
                id = id,
                userId = userId,
                lectureId = lectureId,
            )
        }
    }
}