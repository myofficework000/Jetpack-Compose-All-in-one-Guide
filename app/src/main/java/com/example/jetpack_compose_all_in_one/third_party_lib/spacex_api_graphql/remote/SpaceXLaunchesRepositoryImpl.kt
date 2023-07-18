package com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Optional
import com.example.jetpack_compose_all_in_one.GetLaunchesPastQuery
import com.example.jetpack_compose_all_in_one.di.SpaceXAPI
import javax.inject.Inject

class SpaceXLaunchesRepositoryImpl @Inject constructor(
    @SpaceXAPI private val apolloClient: ApolloClient
): SpaceXLaunchesRepository {
    override suspend fun getPastLaunches(limit: Int) = apolloClient.query(
        GetLaunchesPastQuery(Optional.Present(limit))
    ).execute()
}

interface SpaceXLaunchesRepository {
    suspend fun getPastLaunches(limit: Int = 20): ApolloResponse<GetLaunchesPastQuery.Data>
}