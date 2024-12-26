package io.hhplus.cleanarchitecture.api.lecture.facade

import io.hhplus.cleanarchitecture.domain.lecture.ApplicationRepository
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository
import io.hhplus.cleanarchitecture.support.IntegrationTestSupport
import io.hhplus.cleanarchitecture.support.fixture.TestFixtureUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LectureFacadeTest(
    private val lectureFacade: LectureFacade,
    private val lectureRepository: LectureRepository,
    private val applicationRepository: ApplicationRepository,
) : IntegrationTestSupport() {

    @Test
    fun `유저 ID를 통해 유저의 신청 완료된 특강 목록 조회를 할 수 있다`() {
        // given
        val userId = 1L
        val lecture1 = lectureRepository.save(TestFixtureUtils.lecture())
        val lecture2 = lectureRepository.save(TestFixtureUtils.lecture())
        val lecture3 = lectureRepository.save(TestFixtureUtils.lecture())

        val lectureApplication1 = TestFixtureUtils.lectureApplication(lectureId = lecture1.id, userId = userId)
        val lectureApplication2 = TestFixtureUtils.lectureApplication(lectureId = lecture2.id, userId = userId)
        applicationRepository.save(lectureApplication1)
        applicationRepository.save(lectureApplication2)

        // when
        val result = lectureFacade.getAppliedLectures(userId = userId)

        // then
        assertThat(result)
            .hasSize(2)
    }
}