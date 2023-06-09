package com.example.jetpack_compose_all_in_one.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.databinding.ActivityComposeWithOldWayExampleBinding
import com.example.jetpack_compose_all_in_one.ui.theme.BackgroundColor
import com.example.jetpack_compose_all_in_one.ui.theme.sp_25

class ComposeWithOldWayActivityExample : AppCompatActivity() {
    private lateinit var binding: ActivityComposeWithOldWayExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComposeWithOldWayExampleBinding.inflate(layoutInflater)
        setTheme(R.style.CustomTheme)
        setContentView(binding.root)
        binding.composeView.setContent {
            ComposeContent()
        }
    }
}

@Composable
fun ComposeContent() {
    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Text(
            text = stringResource(id = R.string.compose_text), fontSize = sp_25,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}