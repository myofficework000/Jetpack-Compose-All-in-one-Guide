package com.example.jetpack_compose_all_in_one.demos.github_api.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUser
import com.example.jetpack_compose_all_in_one.demos.github_api.presentation.viewmodel.GithubUserViewModel


@Composable
fun GithubUserListScreen(viewModel: GithubUserViewModel = hiltViewModel()){
    val users = viewModel.users.collectAsState().value

    LazyColumn {
        items(users) { user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(user: GithubUser) {
    Column {
        val painter = rememberImagePainter(data = user.avatar_url)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(text = "Username: ${user.login}")
        Text(text = "ID: ${user.id}")
    }
}
