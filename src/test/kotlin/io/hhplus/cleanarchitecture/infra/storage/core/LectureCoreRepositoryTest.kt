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

    @Test
    fun `특강 정보를 저장할 수 있다`() {
        // given
        val lecture = TestFixtureUtils.lecture()

        // when
        lectureCoreRepository.save(lecture)
        val result = lectureJpaRepository.findByIdOrThrow(lecture.id)

        // then
        assertThat(result.title).isEqualTo(lecture.title)
        assertThat(result.applicationCount).isEqualTo(lecture.applicationCount)
        assertThat(result.maxCapacity).isEqualTo(lecture.maxCapacity)
        assertThat(result.lecturer).isEqualTo(lecture.lecturer)
        assertThat(result.lectureDatetime).isEqualTo(lecture.lectureDatetime)
    }

    @Test
    fun `날짜에 해당하는 특강 목록을 조회할 수 있다`() {
        // given
        val targetDate = LocalDate.of(2024, 12, 28)

        lectureJpaRepository.save(
            TestFixtureUtils.lecture(
                lectureDatetime = LocalDateTime.of(LocalDate.of(2024, 12, 28), LocalTime.MIN)
            )
        )

        lectureJpaRepository.save(
            TestFixtureUtils.lecture(
                lectureDatetime = LocalDateTime.of(LocalDate.of(2024, 12, 28), LocalTime.MAX.withNano(999999000))
            )
        )

        lectureJpaRepository.save(
            TestFixtureUtils.lecture(
                lectureDatetime = LocalDateTime.of(LocalDate.of(2024, 12, 28).plusDays(1), LocalTime.MIN)
            )
        )

        println("LocalDateTime.of(targetDate, LocalTime.MIN) = ${LocalDateTime.of(targetDate, LocalTime.MIN)}")
        println("LocalDateTime.of(targetDate, LocalTime.MAX) = ${LocalDateTime.of(targetDate, LocalTime.MAX)}")
        println(
            "LocalDateTime.of(targetDate.plusDays(1), LocalTime.MIN) = ${
                LocalDateTime.of(
                    targetDate.plusDays(1),
                    LocalTime.MIN
                )
            }"
        )

        // when
        val results = lectureJpaRepository.findAll()
        for (result in results) {
            println("result.lectureDatetime = ${result.lectureDatetime}")
        }
        val result = lectureCoreRepository.getByDate(targetDate)

        // then
        assertThat(result).hasSize(2)
    }

    @Test
    fun `특강 ID 목록에 해당하는 특강 목록을 조회할 수 있다`() {
        // given
        val lectures = lectureJpaRepository.saveAll(
            listOf(
                TestFixtureUtils.lecture(),
                TestFixtureUtils.lecture(),
                TestFixtureUtils.lecture(),
            )
        )
        val lectureIds = lectures.map { it.id }

        // when
        val result = lectureCoreRepository.getByIdIn(lectureIds)

        // then
        assertThat(result).hasSize(lectures.size)
    }
}