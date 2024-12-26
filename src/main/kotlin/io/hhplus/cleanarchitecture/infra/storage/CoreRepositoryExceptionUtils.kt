package io.hhplus.cleanarchitecture.infra.storage

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id)?: throw EntityNotFoundException("Entity not found. ID = $id")
}
