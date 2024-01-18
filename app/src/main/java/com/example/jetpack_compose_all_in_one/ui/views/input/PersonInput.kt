package com.example.jetpack_compose_all_in_one.ui.views.input

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20

@Composable
fun PersonInput() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (hobbyTf, sportTf) = createRefs()
        val hobby = remember { mutableStateOf("") }
        val favoriteSport = remember { mutableStateOf("") }


        TextField(
            value = hobby.value,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Hobby"
                )
            },
            modifier = Modifier
                .constrainAs(hobbyTf)
                {
                    top.linkTo(parent.top, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = { hobby.value = it },
            label = { Text(text = "Your hobby") },
            placeholder = { Text(text = "Type your hobby") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = favoriteSport.value,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Sports,
                    contentDescription = "Sport"
                )
            },
            modifier = Modifier
                .constrainAs(sportTf)
                {
                    top.linkTo(hobbyTf.bottom, margin = dp_20)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = { favoriteSport.value = it },
            label = { Text(text = "Favorite sport") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}