package io.hhplus.cleanarchitecture.domain.lecture.model

import io.hhplus.cleanarchitecture.infra.storage.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "lecture")
class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "title")
    val title: String,

    @Column(name = "max_capacity")
    val maxCapacity: Int,

    @Column(name = "lecturer")
    val lecturer: String,

    @Column(name = "lecture_datetime")
    val lectureDatetime: LocalDateTime,

    @Column(name = "application_count")
    var applicationCount: Int,
) : BaseTimeEntity() {

    fun incrementApplyCount() {
        if (applicationCount >= maxCapacity) {
            throw IllegalStateException("최대 정원 수를 초과할 수 없습니다.")
        }
        this.applicationCount++
    }

    fun isAvailableApply(): Boolean {
        return this.applicationCount < this.maxCapacity
    }

}