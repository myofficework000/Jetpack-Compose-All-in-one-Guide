package com.example.jetpack_compose_all_in_one.features.profile_update

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProfileDao {

    @Query("Select * from profile")
    fun getProfileData() : Profile

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProfileData(profile: Profile)

    @Update
    fun updateProfileData(profile: Profile)

    @Delete
    fun deleteProfileData(profile: Profile)
}