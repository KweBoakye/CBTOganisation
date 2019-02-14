package com.fyp.kweku.cbtoganisation.domain.Repository

interface ModelMapper<E, M> {
    fun fromEntity(from: E): M
    fun toEntity(from: M): E
}