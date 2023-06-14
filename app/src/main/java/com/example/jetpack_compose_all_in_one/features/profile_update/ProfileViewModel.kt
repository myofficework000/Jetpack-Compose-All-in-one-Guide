package com.example.jetpack_compose_all_in_one.features.profile_update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.features.alarm.database.AlarmDao
import com.example.jetpack_compose_all_in_one.features.alarm.database.AlarmInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileDao: ProfileDao
) : ViewModel() {
    var profileData: MutableLiveData<List<Profile>> = MutableLiveData()

    init {
        profileDao.getProfileData()
    }


    fun addProfile(profile: Profile) {
        profileDao.insertProfileData(profile)
    }

    fun updateProfile(profile: Profile) {
        profileDao.updateProfileData(profile)
    }

     fun getProfileData() {
        val profileInfo = profileDao.getProfileData()
        profileData.value = profileInfo

    }
}