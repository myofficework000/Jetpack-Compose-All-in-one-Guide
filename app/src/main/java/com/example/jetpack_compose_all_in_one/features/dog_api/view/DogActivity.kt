package com.example.jetpack_compose_all_in_one.features.dog_api.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.jetpack_compose_all_in_one.databinding.ActivityDogBinding
import com.example.jetpack_compose_all_in_one.features.dog_api.model.data.DogResponse
import com.example.jetpack_compose_all_in_one.features.dog_api.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DogActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDogBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        viewModel.getDogImageFromApi()
    }

    private fun setUpObserver() {
        binding.btnGet.setOnClickListener {
            viewModel.dogLiveData.observe(this@DogActivity){
                Glide.with(applicationContext)
                    .load(it.url).into(binding.ivDog)
            }
        }
    }
}