package io.hhplus.cleanarchitecture.api.lecture.facade

import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.AppliedLectureResult
import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.AvailableLectureResult
import io.hhplus.cleanarchitecture.domain.lecture.ApplicationService
import io.hhplus.cleanarchitecture.domain.lecture.LectureService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class LectureFacade(
    private val lectureService: LectureService,
    private val applicationService: ApplicationService,
) {
    /**
     * 신청 가능 특강 목록 조회
     */
    fun getAvailableLectures(date: LocalDate): List<AvailableLectureResult> {
        val lectures = lectureService.getAvailableLectures(date)
        return lectures.map {
            AvailableLectureResult.from(it)
        }
    }

    /**
     * 신청 완료된 특강 목록 조회
     */
    fun getAppliedLectures(userId: Long): List<AppliedLectureResult> {
        val applications = applicationService.getAllByUserId(userId)
        val lectureIds = applications.map { it.lectureId }

        return lectureService.getByIdIn(lectureIds).map {
            AppliedLectureResult.from(it)
        }
    }

}