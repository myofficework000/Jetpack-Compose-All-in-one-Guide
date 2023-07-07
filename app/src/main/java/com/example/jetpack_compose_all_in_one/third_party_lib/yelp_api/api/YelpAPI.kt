package com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.api

import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.data.BusinessSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpAPI {

    @GET("businesses/search")
    suspend fun getBusinesses(
        @Header("Authorization") authToken: String = "Bearer zMz9UhVZ1yQa1rxllGqZBA2he6364davsAY8Rou9zhaZVDXp0AS9lFI6SWzNpskZiR1XDAKs__Bwqnap3DWNEF7VWvxoBfRTxIRdzYehenrLO7q8jeUqDkt7RmyYZHYx",
        @Query("location") location: String = "NYC",
        @Query("sort_by") sortBy: String = "best_match",
        @Query("limit") limit: Int = 10,

        ): BusinessSearchResponse
}