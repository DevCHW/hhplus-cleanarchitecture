package io.hhplus.cleanarchitecture.domain.lecture.model

import io.hhplus.cleanarchitecture.infra.storage.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "lecture_application")
class LectureApplication(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "lecture_id")
    val lectureId: Long,

    @Column(name = "user_id")
    val userId: Long,
) : BaseTimeEntity()