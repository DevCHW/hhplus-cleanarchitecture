package io.hhplus.cleanarchitecture.domain.lecture

import io.hhplus.cleanarchitecture.domain.lecture.model.LectureApplication

interface ApplicationRepository {

    fun save(lectureApplication: LectureApplication): LectureApplication

    fun isExistBy(userId: Long, lectureId: Long): Boolean

    fun getAllApplicationsByUserId(userId: Long): List<LectureApplication>
}