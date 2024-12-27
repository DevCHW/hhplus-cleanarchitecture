package io.hhplus.cleanarchitecture.domain.lecture.model

import io.hhplus.cleanarchitecture.support.fixture.TestFixtureUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LectureTest {

    @Nested
    @DisplayName("신청 수 1 증가")
    inner class IncrementApplyCount {
        @Test
        fun `신청 수를 1 증가시킬 수 있다`() {
            // given
            val maxCapacity = 1
            val applyCount = 0

            val lecture = TestFixtureUtils.lecture(
                maxCapacity = maxCapacity,
                applicationCount = applyCount
            )

            // when
            lecture.incrementApplyCount()

            // then
            assertThat(lecture.applicationCount).isEqualTo(applyCount + 1)
        }

        @Test
        fun `최대 정원 초과 시 예외가 발생한다`() {
            // given
            val maxCapacity = 10

            val lecture = TestFixtureUtils.lecture(
                maxCapacity = maxCapacity,
                applicationCount = 10
            )

            // when & then
            assertThatThrownBy {
                lecture.incrementApplyCount()
            }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("최대 정원 수를 초과할 수 없습니다.")
        }
    }

    @Nested
    @DisplayName("특강 신청 가능 여부 반환")
    inner class IsAvailableApply {
        @Test
        fun `특강 신청 수가 최대 정원 수가 미만인 경우 True를 반환한다`() {
            // given
            val lecture = TestFixtureUtils.lecture(
                maxCapacity = 30,
                applicationCount = 29,
            )

            // when
            val result = lecture.isAvailableApply()

            // then
            assertThat(result).isTrue()
        }

        @Test
        fun `특강 신청 수가 최대 정원 수 이상인 경우 False를 반환한다`() {
            // given
            val lecture = TestFixtureUtils.lecture(
                maxCapacity = 30,
                applicationCount = 30,
            )

            // when
            val result = lecture.isAvailableApply()

            // then
            assertThat(result).isFalse()
        }
    }

}
