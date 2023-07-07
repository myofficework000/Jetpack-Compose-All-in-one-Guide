package com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.repo


import com.example.jetpack_compose_all_in_one.di.YelpAPIAnnotation
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.api.YelpAPI
import javax.inject.Inject

class YelpRepo  @Inject constructor(@YelpAPIAnnotation private val api: YelpAPI) {

    suspend fun getBusinesses() = api.getBusinesses()
}