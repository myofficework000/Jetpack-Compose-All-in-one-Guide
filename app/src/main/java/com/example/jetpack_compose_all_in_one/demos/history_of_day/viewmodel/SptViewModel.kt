package com.technocraze.mvvmdatehistroy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// for testing


class SpyViewModel : ViewModel() {

  val data = MutableLiveData<String>()

  fun updateData(newData: String) {
    data.postValue(newData)
  }
}