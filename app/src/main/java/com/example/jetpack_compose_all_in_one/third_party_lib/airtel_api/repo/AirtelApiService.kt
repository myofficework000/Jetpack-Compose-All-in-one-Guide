package com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo

import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.Constants.AIRTEL_URL_END
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.model.AirtelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AirtelApiService {

    @GET("{ip_address}/$AIRTEL_URL_END")
    suspend fun retrieveLocationDetails(@Path(value = "ip_address") ip_address: String): Response<AirtelResponse>

}