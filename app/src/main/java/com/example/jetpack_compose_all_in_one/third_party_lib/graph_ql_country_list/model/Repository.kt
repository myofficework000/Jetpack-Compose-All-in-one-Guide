package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model

import com.apollographql.apollo3.ApolloClient
import com.example.jetpack_compose_all_in_one.FindCountriesOfAContinentQuery
import com.example.jetpack_compose_all_in_one.GetContinentsQuery

class Repository(private val apolloClient: ApolloClient) {
    suspend fun getContinents() =
        apolloClient.query(GetContinentsQuery()).execute()

    suspend fun getCountriesOfSelectedContinent(continentCode: String) = apolloClient.query(
        FindCountriesOfAContinentQuery(continentCode)
    ).execute()
}