package io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface LectureJpaRepository : JpaRepository<Lecture, Long> {

    fun findAllByLectureDatetimeBetween(
        @Param("startedAt") startedAt: LocalDateTime,
        @Param("endedAt") endedAt: LocalDateTime
    ): List<Lecture>

    fun findByIdIn(lectureIds: List<Long>): List<Lecture>
}
