package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.RemoteNewRepository
import com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel.NewsState
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class StateModule {

     @Singleton
     @Provides
     @MviNewsAPI
     fun provideNewsStates(repository: RemoteNewRepository):NewsState{
         return NewsState(repository)
     }
}