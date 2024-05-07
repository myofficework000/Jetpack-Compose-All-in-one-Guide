package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.mapper

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogEntityCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogResponseCleanCode

object DogMapperCleanCode: Mapper<DogEntityCleanCode, DogResponseCleanCode> {
    override fun mapFromEntity(type: DogEntityCleanCode) = DogResponseCleanCode(
        message = type.dogUrl,
        status = type.status
    )

    override fun mapToEntity(type: DogResponseCleanCode) = DogEntityCleanCode(
        dogUrl = type.message,
        status = type.status
    )
}