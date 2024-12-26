package io.hhplus.cleanarchitecture.api.lecture.controller.dto.response

import io.hhplus.cleanarchitecture.api.lecture.facade.dto.result.ApplyLectureResult

data class CreateApplicationResponse(
    val userId: Long,
    val lectureId: Long,
    val message: String,
) {
    companion object {
        fun from(applyLectureResult: ApplyLectureResult): CreateApplicationResponse {
            return CreateApplicationResponse(
                userId = applyLectureResult.userId,
                lectureId = applyLectureResult.lectureId,
                message = "특강 신청이 성공적으로 완료되었습니다.",
            )
        }
    }
}
