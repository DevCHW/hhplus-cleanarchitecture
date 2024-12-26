package io.hhplus.cleanarchitecture.api.lecture.facade

import com.example.ktboard.domain.error.CoreApiException
import io.hhplus.cleanarchitecture.domain.lecture.ApplicationRepository
import io.hhplus.cleanarchitecture.domain.lecture.LectureRepository
import io.hhplus.cleanarchitecture.support.IntegrationTestSupport
import io.hhplus.cleanarchitecture.support.concurrent.ConcurrencyTestUtils
import io.hhplus.cleanarchitecture.support.fixture.TestFixtureUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class ApplicationFacadeTest(
    private val applicationFacade: ApplicationFacade,
    private val lectureRepository: LectureRepository,
    private val applicationRepository: ApplicationRepository,
) : IntegrationTestSupport() {


    @Nested
    @DisplayName("특강 신청")
    inner class Apply {
        @Test
        fun `유저 ID와 특강 ID를 통해 특강 신청을 할 수 있다`() {
            // given
            val userId = 1L
            val lecture = lectureRepository.save(TestFixtureUtils.lecture())

            // when
            val result = applicationFacade.apply(userId, lecture.id)

            // then
            assertThat(result).isNotNull
            assertThat(result.userId).isEqualTo(userId)
            assertThat(result.lectureId).isEqualTo(lecture.id)
        }

        @Test
        fun `신청한 특강이 존재하지 않는 경우 예외가 발생한다`() {
            // given
            val userId = 1L
            val lectureId = 1L

            // when & then
            assertThatThrownBy {
                applicationFacade.apply(userId, lectureId)
            }
                .isInstanceOf(CoreApiException::class.java)
                .hasMessage("존재하지 않는 특강입니다.")
        }

        @Test
        fun `이미 신청한 특강에 대해 다시 신청한 경우 예외가 발생한다`() {
            // given
            val userId = 1L
            val lecture = lectureRepository.save(TestFixtureUtils.lecture())
            applicationRepository.save(
                TestFixtureUtils.lectureApplication(
                    userId = userId,
                    lectureId = lecture.id,
                )
            )

            // when & then
            assertThatThrownBy {
                applicationFacade.apply(userId, lecture.id)
            }
                .isInstanceOf(CoreApiException::class.java)
                .hasMessage("이미 신청한 특강입니다.")
        }

        @Test
        fun `특강 신청 정원이 초과된 경우 예외가 발생한다`() {
            // given
            val userId = 1L
            val lecture = lectureRepository.save(
                TestFixtureUtils.lecture(
                    maxCapacity = 30,
                    applyCount = 30
                )
            )

            // when & then
            assertThatThrownBy {
                applicationFacade.apply(userId, lecture.id)
            }
                .isInstanceOf(CoreApiException::class.java)
                .hasMessage("신청한 특강의 정원이 초과되었습니다.")
        }
    }
}