package io.hhplus.cleanarchitecture.infra.storage.core

import io.hhplus.cleanarchitecture.infra.storage.core.lecture.LectureApplicationCoreRepository
import io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa.LectureApplicationJpaRepository
import io.hhplus.cleanarchitecture.infra.storage.findByIdOrThrow
import io.hhplus.cleanarchitecture.support.IntegrationTestSupport
import io.hhplus.cleanarchitecture.support.fixture.TestFixtureUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LectureApplicationCoreRepositoryTest(
    private val lectureApplicationCoreRepository: LectureApplicationCoreRepository,
    private val lectureApplicationJpaRepository: LectureApplicationJpaRepository,
) : IntegrationTestSupport() {

    @Test
    fun `특강 신청 정보를 저장할 수 있다`() {
        // given
        val lectureApplication = TestFixtureUtils.lectureApplication()

        // when
        val savedLectureApplication = lectureApplicationCoreRepository.save(lectureApplication)
        val result = lectureApplicationJpaRepository.findByIdOrThrow(savedLectureApplication.id)

        // then
        assertThat(result.id).isEqualTo(lectureApplication.id)
        assertThat(result.lectureId).isEqualTo(lectureApplication.lectureId)
        assertThat(result.userId).isEqualTo(lectureApplication.userId)
    }

    @Nested
    @DisplayName("유저 특강 신청 내역 존재 여부 조회")
    inner class IsExistBy {
        @Test
        fun `유저의 특강 신청 내역이 존재하면 True를 반환한다`() {
            // given
            val userId = 1L
            val lectureId = 1L
            val lectureApplication = TestFixtureUtils.lectureApplication(userId = userId, lectureId = lectureId)
            lectureApplicationJpaRepository.save(lectureApplication)

            // when
            val result = lectureApplicationCoreRepository.isExistBy(userId, lectureId)

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `유저의 특강 신청 내역이 존재하지 않는다면 False를 반환한다`() {
            // given
            val userId = 1L
            val lectureId = 1L

            // when
            val result = lectureApplicationCoreRepository.isExistBy(userId, lectureId)

            // then
            assertThat(result).isFalse()
        }
    }
}