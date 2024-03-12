package com.example.jetpack_compose_all_in_one.di

import android.content.Context
import androidx.room.Room
import com.example.jetpack_compose_all_in_one.features.alarm.database.AppDatabase
import com.example.jetpack_compose_all_in_one.features.profile_update.migration1To2
import com.example.jetpack_compose_all_in_one.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing dependencies related to Room database.
 */
@Module
@InstallIn(SingletonComponent::class)
class RoomModules {
    /**
     * Provides an instance of the Room database.
     * @param context Application context.
     * @return Instance of the Room database.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.ROOMDB_DBNAME
    )
        .allowMainThreadQueries()
        .addMigrations(migration1To2)
        .build()

    /**
     * Provides an instance of the AlarmDao.
     * @param db Instance of the Room database.
     * @return Instance of the AlarmDao.
     */
    @Provides
    @Singleton
    fun provideAlarmDao(db: AppDatabase) = db.getAlarmDao()

    /**
     * Provides an instance of the ProfileDao.
     * @param db Instance of the Room database.
     * @return Instance of the ProfileDao.
     */
    @Provides
    @Singleton
    fun provideProfileDao(db: AppDatabase) = db.getProfileDao()
}
