package com.example.jetpack_compose_all_in_one.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.databinding.ActivityComposeWithOldWayExampleBinding
import com.example.jetpack_compose_all_in_one.features.profile.ProfileScreen

class ComposeWithOldWayActivityExample : AppCompatActivity() {
    private lateinit var binding: ActivityComposeWithOldWayExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposeWithOldWayExampleBinding.inflate(layoutInflater)
        setTheme(R.style.CustomTheme)
        setContentView(binding.root)
        binding.composeView.setContent {
            ProfileScreen()
        }
    }
}