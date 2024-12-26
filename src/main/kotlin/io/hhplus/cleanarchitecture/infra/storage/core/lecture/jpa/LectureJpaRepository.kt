package io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa

import io.hhplus.cleanarchitecture.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureJpaRepository : JpaRepository<Lecture, Long>