package io.hhplus.cleanarchitecture.domain.lecture

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import java.time.LocalDate

interface LectureRepository {

    fun save(lecture: Lecture): Lecture

    fun isExistById(lectureId: Long): Boolean

    fun getById(lectureId: Long): Lecture

    fun getByIdIn(lectureIds: List<Long>): List<Lecture>

    fun getByDate(date: LocalDate): List<Lecture>

    fun getByIdWithLock(lectureId: Long): Lecture
}