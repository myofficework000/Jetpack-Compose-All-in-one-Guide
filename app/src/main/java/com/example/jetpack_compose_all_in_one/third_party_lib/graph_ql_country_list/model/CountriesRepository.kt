package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.jetpack_compose_all_in_one.FindCountriesOfAContinentQuery
import com.example.jetpack_compose_all_in_one.GetContinentsQuery
import com.example.jetpack_compose_all_in_one.di.CountryAPI
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor (
   @CountryAPI private val apolloClient: ApolloClient
): CountriesRepository {
    override suspend fun getContinents() =
        apolloClient.query(GetContinentsQuery()).execute()

    override suspend fun getCountriesOfSelectedContinent(continentCode: String) = apolloClient.query(
        FindCountriesOfAContinentQuery(continentCode)
    ).execute()
}

interface CountriesRepository {
    suspend fun getContinents(): ApolloResponse<GetContinentsQuery.Data>

    suspend fun getCountriesOfSelectedContinent(continentCode: String):
            ApolloResponse<FindCountriesOfAContinentQuery.Data>
}