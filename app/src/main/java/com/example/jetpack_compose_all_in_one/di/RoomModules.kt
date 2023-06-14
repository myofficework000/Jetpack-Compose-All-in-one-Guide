package com.example.jetpack_compose_all_in_one.di

import android.content.Context
import androidx.room.Room
import com.example.jetpack_compose_all_in_one.features.alarm.database.AppDatabase
import com.example.jetpack_compose_all_in_one.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModules {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.ROOMDB_DBNAME
    )
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideAlarmDao(db: AppDatabase) = db.getAlarmDao()

    @Provides
    @Singleton
    fun provideProfileDao(db: AppDatabase) = db.getProfileDao()
}