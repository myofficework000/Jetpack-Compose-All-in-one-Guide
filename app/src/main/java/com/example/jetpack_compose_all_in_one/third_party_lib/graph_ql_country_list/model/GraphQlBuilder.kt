package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import okhttp3.OkHttpClient

object GraphQlBuilder {

    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com")
            .okHttpClient(okHttpClient)
            .build()
    }

}
