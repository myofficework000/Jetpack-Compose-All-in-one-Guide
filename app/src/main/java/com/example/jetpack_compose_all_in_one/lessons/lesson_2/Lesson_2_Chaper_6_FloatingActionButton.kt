package com.example.jetpack_compose_all_in_one.lessons.lesson_2

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAlarm
import androidx.compose.material.icons.rounded.CurrencyBitcoin
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.theme.Green100
import com.example.jetpack_compose_all_in_one.ui.theme.GreenA100
import com.example.jetpack_compose_all_in_one.ui.theme.LavenderA100
import com.example.jetpack_compose_all_in_one.ui.theme.MintA100
import com.example.jetpack_compose_all_in_one.ui.theme.OceanA100
import com.example.jetpack_compose_all_in_one.ui.theme.PinkA100
import com.example.jetpack_compose_all_in_one.ui.theme.SkyA100
import com.example.jetpack_compose_all_in_one.ui.theme.VioletA100
import com.example.jetpack_compose_all_in_one.ui.theme.YellowA100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16

@Preview
@Composable
fun Lesson_2_Chapter_6_FloatingActionButton() {
    FAB()
}

@Composable
fun FAB() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val ctx = LocalContext.current
        FloatingActionButton(
            onClick = { Toast.makeText(ctx, "Setting FAB", Toast.LENGTH_SHORT).show() },
            containerColor = Green100,
            shape = RoundedCornerShape(dp_16),
        ) {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Add FAB",
                tint = SkyA100
            )
        }

        FloatingActionButton(
            onClick = { Toast.makeText(ctx, "Share FAB", Toast.LENGTH_SHORT).show() },
            containerColor = LavenderA100,
            shape = RoundedCornerShape(dp_16),
        ) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = "Share Icon",
                tint = VioletA100
            )
            
        }
        
        FloatingActionButton(
            onClick = { Toast.makeText(ctx, "Alarm FAB", Toast.LENGTH_SHORT).show() },
            containerColor = GreenA100,
            shape = RoundedCornerShape(dp_16),
        ) {
            Icon(
                imageVector = Icons.Rounded.AddAlarm,
                contentDescription = "Add Alarm",
                tint = OceanA100
            )
        }

        FloatingActionButton(
            onClick = { Toast.makeText(ctx, "Bitcoin FAB", Toast.LENGTH_SHORT).show() },
            containerColor = PinkA100,
            shape = RoundedCornerShape(20)

        ) {
            Icon(
                imageVector = Icons.Rounded.CurrencyBitcoin,
                contentDescription = "Add Bitcoin",
                tint = White
            )
        }

        ExtendedFloatingActionButton(
            onClick = { Toast.makeText(ctx, "Shopping FAB", Toast.LENGTH_SHORT).show() },
            containerColor = MintA100,
            shape = RoundedCornerShape(20),
            text = { Text(text = "Shopping Bag", color = Black )},
            icon = {
                Icon(
                    imageVector = Icons.Rounded.ShoppingBag,
                    contentDescription = "Checkout FAB",
                    tint = YellowA100
                ) }

        )
    }
}
