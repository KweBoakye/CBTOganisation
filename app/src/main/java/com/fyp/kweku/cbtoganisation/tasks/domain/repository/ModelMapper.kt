package com.fyp.kweku.cbtoganisation.tasks.domain.repository

interface ModelMapper<E, M> {
    fun fromEntity(from: E): M
    fun toEntity(from: M): E
}