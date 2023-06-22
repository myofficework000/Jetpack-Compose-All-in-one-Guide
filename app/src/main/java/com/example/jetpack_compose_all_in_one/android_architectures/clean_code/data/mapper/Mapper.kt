package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.mapper

interface Mapper<E, D> {
    fun mapFromEntity(type: E): D
    fun mapToEntity(type: D): E
}