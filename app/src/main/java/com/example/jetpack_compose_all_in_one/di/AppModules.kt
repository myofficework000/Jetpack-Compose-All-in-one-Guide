package com.example.jetpack_compose_all_in_one.di

import android.content.Context
import com.example.jetpack_compose_all_in_one.lessons.lesson_3.PersonRepository
import com.example.jetpack_compose_all_in_one.lessons.lesson_3.PersonRepositoryImpl
import com.example.jetpack_compose_all_in_one.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context) =
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePersonRepository(): PersonRepository {
        return PersonRepositoryImpl()
    }
}