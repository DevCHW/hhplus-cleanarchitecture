package io.hhplus.cleanarchitecture.infra.storage.core

import io.hhplus.cleanarchitecture.infra.storage.core.lecture.LectureCoreRepository
import io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa.LectureJpaRepository
import io.hhplus.cleanarchitecture.infra.storage.findByIdOrThrow
import io.hhplus.cleanarchitecture.support.IntegrationTestSupport
import io.hhplus.cleanarchitecture.support.fixture.TestFixtureUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class LectureCoreRepositoryTest(
    private val lectureCoreRepository: LectureCoreRepository,
    private val lectureJpaRepository: LectureJpaRepository,
) : IntegrationTestSupport() {

    @Nested
    @DisplayName("특강 ID로 특강 조회")
    inner class GetById {
        @Test
        fun `특강 ID에 해당하는 특강을 조회할 수 있다`() {
            // given
            val lecture = lectureJpaRepository.save(TestFixtureUtils.lecture())

            // when
            val result = lectureCoreRepository.getById(lecture.id)

            // then
            assertThat(result.id).isEqualTo(lecture.id)
            assertThat(result.title).isEqualTo(lecture.title)
        }

        @Test
        fun `조회된 특강이 없다면 예외가 발생한다`() {
            // given
            val lectureId = 1L

            // when & then
            assertThatThrownBy {
                lectureCoreRepository.getById(lectureId)
            }
                .isInstanceOf(JpaObjectRetrievalFailureException::class.java)
                .hasMessage("Entity not found. ID = $lectureId")
        }
    }

    @Nested
    @DisplayName("특강 ID로 특강 존재 여부 조회")
    inner class ExistsById {
        @Test
        fun `ID에 해당하는 특강이 존재할 경우 True를 반환한다`() {
            // given
            val lecture = lectureJpaRepository.save(TestFixtureUtils.lecture())

            // when
            val result = lectureCoreRepository.isExistById(lecture.id)

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `ID에 해당하는 특강이 존재하지 않을 경우 False를 반환한다`() {
            // when
            val result = lectureCoreRepository.isExistById(1L)

            // then
            assertThat(result).isFalse()
        }
    }
}