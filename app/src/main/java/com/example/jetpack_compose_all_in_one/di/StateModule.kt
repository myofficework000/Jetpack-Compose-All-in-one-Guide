package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.RemoteNewRepository
import com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel.NewsState
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing dependencies related to state management.
 */
@Module
@InstallIn(SingletonComponent::class)
class StateModule {

    /**
     * Provides an instance of NewsState for MVI (Model-View-Intent) architecture.
     * @param repository RemoteNewRepository instance.
     * @return Instance of NewsState.
     */
    @Singleton
    @Provides
    @MviNewsAPI
    fun provideNewsStates(repository: RemoteNewRepository): NewsState {
        return NewsState(repository)
    }
}
