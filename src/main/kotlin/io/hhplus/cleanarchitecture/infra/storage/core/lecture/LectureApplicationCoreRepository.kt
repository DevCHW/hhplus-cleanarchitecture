package io.hhplus.cleanarchitecture.infra.storage.core.lecture

import io.hhplus.cleanarchitecture.domain.lecture.ApplicationRepository
import io.hhplus.cleanarchitecture.domain.lecture.model.LectureApplication
import io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa.LectureApplicationJpaRepository
import org.springframework.stereotype.Repository

@Repository
class LectureApplicationCoreRepository(
    private val lectureApplicationJpaRepository: LectureApplicationJpaRepository,
) : ApplicationRepository {

    override fun save(lectureApplication: LectureApplication): LectureApplication {
        return lectureApplicationJpaRepository.save(lectureApplication)
    }

    override fun isExistBy(userId: Long, lectureId: Long): Boolean {
        return lectureApplicationJpaRepository.existsByUserIdAndLectureId(userId, lectureId)
    }

    override fun getAllApplicationsByUserId(userId: Long): List<LectureApplication> {
        return lectureApplicationJpaRepository.findByUserId(userId)
    }

}