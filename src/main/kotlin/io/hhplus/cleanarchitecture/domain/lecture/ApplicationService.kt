package io.hhplus.cleanarchitecture.domain.lecture

import io.hhplus.cleanarchitecture.domain.lecture.model.LectureApplication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ApplicationService(
    private val applicationRepository: ApplicationRepository,
) {

    /**
     * 특강 신청 완료 목록 조회
     */
    @Transactional(readOnly = true)
    fun getAllByUserId(userId: Long): List<LectureApplication> {
        return applicationRepository.getAllApplicationsByUserId(userId)
    }

    /**
     * 특강 신청 생성
     */
    @Transactional
    fun createApplication(userId: Long, lectureId: Long): LectureApplication {
        // 특강 신청 정보 저장
        return applicationRepository.save(
            LectureApplication(
                userId = userId,
                lectureId = lectureId,
            )
        )
    }

    /**
     * 특강 신청 존재 여부 조회
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun isExistBy(lectureId: Long, userId: Long): Boolean {
        return applicationRepository.isExistBy(lectureId, userId)
    }

}