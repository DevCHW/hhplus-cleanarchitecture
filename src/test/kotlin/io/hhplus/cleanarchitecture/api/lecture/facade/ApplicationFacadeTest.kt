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
                    applicationCount = 30
                )
            )

            // when & then
            assertThatThrownBy {
                applicationFacade.apply(userId, lecture.id)
            }
                .isInstanceOf(CoreApiException::class.java)
                .hasMessage("신청한 특강의 정원이 초과되었습니다.")
        }

        @Test
        fun `동시에 동일한 특강에 대해 40명이 신청했을 때, 특강의 최대 정원 수 만큼만 신청에 성공해야 한다`() {
            // given
            val maxCapacity = 30
            val lecture = lectureRepository.save(
                TestFixtureUtils.lecture(
                    maxCapacity = maxCapacity,
                    applicationCount = 0
                )
            )

            val userCount = 40
            val actions = mutableListOf<Runnable>()
            val successCount = AtomicInteger()
            val errorCount = AtomicInteger()

            // 각각 다른 유저 40명이 동일한 특강을 신청
            for (i in 1..userCount) {
                val userId = i.toLong()
                val action = Runnable {
                    try {
                        applicationFacade.apply(userId, lecture.id)
                        successCount.incrementAndGet() // 신청 성공 시 성공 카운트 증가
                    } catch (e: CoreApiException) {
                        errorCount.incrementAndGet() // 신청 실패 시 실패 카운트 증가
                    }
                }
                actions.add(action)
            }

            // when
            ConcurrencyTestUtils.executeConcurrently(actions)

            // then
            assertThat(successCount.get()).isEqualTo(30)
            assertThat(errorCount.get()).isEqualTo(10)
        }

        @Test
        fun `동일한 유저 정보로 같은 특강을 동시에 5번 신청하더라도 한 번만 성공해야 한다`() {
            // given
            val userId = 1L
            val lecture = lectureRepository.save(TestFixtureUtils.lecture())
            val successCount = AtomicInteger()
            val errorCount = AtomicInteger()

            val action = Runnable {
                try {
                    applicationFacade.apply(userId, lecture.id)
                    successCount.incrementAndGet()
                } catch (e: Exception) {
                    errorCount.incrementAndGet()
                }
            }

            // when
            ConcurrencyTestUtils.executeConcurrently(5, action)

            // then
            assertThat(successCount.get()).isEqualTo(1)
            assertThat(errorCount.get()).isEqualTo(4)
        }
    }
}