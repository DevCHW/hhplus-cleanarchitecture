package io.hhplus.cleanarchitecture.infra.storage.core.lecture

import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository
import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa.LectureJpaRepository
import io.hhplus.cleanarchitecture.infra.storage.findByIdOrThrow
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime

@Repository
class LectureCoreRepository(
    private val lectureJpaRepository: LectureJpaRepository,
) : LectureRepository {

    override fun getById(lectureId: Long): Lecture {
        return lectureJpaRepository.findByIdOrThrow(lectureId)
    }

    override fun save(lecture: Lecture): Lecture {
        return lectureJpaRepository.save(lecture)
    }

    override fun isExistById(lectureId: Long): Boolean {
        return lectureJpaRepository.existsById(lectureId)
    }

    override fun getByDate(date: LocalDate): List<Lecture> {
        return lectureJpaRepository.findAllByLectureDatetimeBetween(date.atStartOfDay(), date.atTime(LocalTime.MAX).withNano(999999000))
    }

    override fun getByIdIn(lectureIds: List<Long>): List<Lecture> {
        return lectureJpaRepository.findByIdIn(lectureIds)
    }
}