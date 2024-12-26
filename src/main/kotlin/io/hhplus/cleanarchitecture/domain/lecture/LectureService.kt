package io.hhplus.cleanarchitecture.domain.lecture

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

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
     * 신청 가능 특강 목록 조회
     */
    @Transactional(readOnly = true)
    fun getAvailableLectures(date: LocalDate): List<Lecture> {
        return lectureRepository.getByDate(date)
            .filter { it.isAvailableApply() }
    }

    /**
     * ID에 해당하는 특강 조회
     */
    @Transactional(readOnly = true)
    fun getById(lectureId: Long): Lecture {
        return lectureRepository.getById(lectureId)
    }

    /**
     * ID 목록에 해당하는 특강 목록 조회
     */
    @Transactional(readOnly = true)
    fun getByIdIn(lectureIds: List<Long>): List<Lecture> {
        return lectureRepository.getByIdIn(lectureIds)
    }

}