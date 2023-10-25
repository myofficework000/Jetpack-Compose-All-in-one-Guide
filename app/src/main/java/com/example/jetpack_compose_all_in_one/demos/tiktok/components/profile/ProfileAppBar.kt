package com.example.jetpack_compose_all_in_one.demos.tiktok.components.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.jetpack_compose_all_in_one.data.model.Album
import com.example.jetpack_compose_all_in_one.R

@Composable
fun ProfileAppBar(album: Album, navHostController: NavHostController) {
    TopAppBar(
        title = { Text(text = album.artist) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_back)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(id = R.string.cd_back)
                )
            }
        }
    )
}
