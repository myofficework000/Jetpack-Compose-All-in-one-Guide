package com.example.jetpack_compose_all_in_one.ui.views.news_ui.detail

import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {


}