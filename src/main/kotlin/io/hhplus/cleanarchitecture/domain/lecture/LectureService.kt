package io.hhplus.cleanarchitecture.domain.lecture

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
) {

    /**
     * 특강 존재 여부 조회
     */
    @Transactional(readOnly = true)
    fun isExist(lectureId: Long): Boolean {
        return lectureRepository.isExistById(lectureId)
    }

    /**
     * ID에 해당하는 특강 조회
     */
    @Transactional(readOnly = true)
    fun getById(lectureId: Long): Lecture {
        return lectureRepository.getById(lectureId)
    }

}