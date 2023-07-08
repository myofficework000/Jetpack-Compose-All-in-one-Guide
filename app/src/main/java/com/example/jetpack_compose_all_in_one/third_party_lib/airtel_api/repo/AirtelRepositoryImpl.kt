package com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo

import com.example.jetpack_compose_all_in_one.di.AirtelAPI
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.model.AirtelResponse
import retrofit2.Response
import javax.inject.Inject

class AirtelRepositoryImpl @Inject constructor(@AirtelAPI private val airtelApiService: AirtelApiService): AirtelRepository {
    override suspend fun getLocationDetails(ip_address: String): Response<AirtelResponse> {
        return airtelApiService.retrieveLocationDetails(ip_address)
    }
}

interface AirtelRepository {
    suspend fun getLocationDetails(ip_address: String): Response<AirtelResponse>
}