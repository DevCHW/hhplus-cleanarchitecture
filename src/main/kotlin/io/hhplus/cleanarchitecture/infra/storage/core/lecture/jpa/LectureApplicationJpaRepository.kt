package io.hhplus.cleanarchitecture.infra.storage.core.lecture.jpa

import io.hhplus.cleanarchitecture.domain.lecture.model.LectureApplication
import org.springframework.data.jpa.repository.JpaRepository

interface LectureApplicationJpaRepository : JpaRepository<LectureApplication, Long>