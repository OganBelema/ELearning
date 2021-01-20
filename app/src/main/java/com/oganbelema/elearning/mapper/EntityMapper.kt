package com.oganbelema.elearning.mapper

/**
 * Created by Belema Ogan on 1/18/21.
 */
interface EntityMapper<E, D> {
    fun fromEntity(entity: E): D
    fun toEntity(model: D): E
    fun fromEntityList(entities: List<E>?): List<D>?
    fun toEntityList(models: List<D>?): List<E>?
}