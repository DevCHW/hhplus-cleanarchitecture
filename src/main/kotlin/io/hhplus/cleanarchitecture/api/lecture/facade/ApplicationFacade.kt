package io.hhplus.cleanarchitecture.api.lecture.facade

import com.example.ktboard.domain.error.CoreApiException
import com.example.ktboard.domain.error.ErrorCode
import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.ApplyLectureResult
import io.hhplus.cleanarchitecture.domain.lecture.ApplicationService
import io.hhplus.cleanarchitecture.domain.lecture.LectureService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ApplicationFacade(
    private val applicationService: ApplicationService,
    private val lectureService: LectureService,
) {
    /**
     * 특강 신청
     */
    @Transactional
    fun apply(userId: Long, lectureId: Long): ApplyLectureResult {
        // 특강 존재 여부 검증
        if (!lectureService.isExist(lectureId)) {
            throw CoreApiException(ErrorCode.LECTURE_NOT_FOUND)
        }

        // 신청 여부 검증
        if (applicationService.isExistBy(lectureId, userId)) {
            throw CoreApiException(ErrorCode.ALREADY_APPLIED)
        }

        // 신청 정원 검증
        val lecture = lectureService.getById(lectureId)
        if (!lecture.isAvailableApply()) {
            throw CoreApiException(ErrorCode.CAPACITY_EXCEEDED)
        }

        lecture.incrementApplyCount()

        val applicationLecture = applicationService.createApplication(userId, lectureId)
        return ApplyLectureResult(
            userId = applicationLecture.userId,
            lectureId = applicationLecture.lectureId,
        )
    }

}