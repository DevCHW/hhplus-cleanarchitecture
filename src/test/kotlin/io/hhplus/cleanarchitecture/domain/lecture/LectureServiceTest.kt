package io.hhplus.cleanarchitecture.domain.lecture

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LectureServiceTest {
    private lateinit var lectureService: LectureService
    private lateinit var lectureRepository: LectureRepository

    @BeforeEach
    fun setUp() {
        lectureRepository = mockk()
        lectureService = LectureService(lectureRepository)
    }

    @Nested
    @DisplayName("특강 존재 여부 조회")
    inner class IsExistLecture {
        @Test
        fun `특강이 존재한다면 True를 반환한다`() {
            // given
            val lectureId = 1L
            every { lectureRepository.isExistById(any()) } returns true

            // when
            val result = lectureService.isExist(lectureId)

            // then
            assertThat(result).isTrue
        }

        @Test
        fun `특강이 존재하지 않는다면 False를 반환한다`() {
            // given
            val lectureId = 1L
            every { lectureRepository.isExistById(any()) } returns false

            // when
            val result = lectureService.isExist(lectureId)

            // then
            assertThat(result).isFalse
        }
    }
}