package io.hhplus.cleanarchitecture.api.lecture.controller.dto.request

import jakarta.validation.constraints.NotNull

data class CreateApplicationRequest(
    @field:NotNull(message = "유저 ID는 필수입니다.")
    val userId: Long,
)