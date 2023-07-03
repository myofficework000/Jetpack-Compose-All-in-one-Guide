package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repo_ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.viewmodel.GitHubViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16


/*@Composable
fun RepositoryList(repositoryViewModel: GitHubViewModel) {
    val repositories: LazyPagingItems<DataResponseItem> = repositoryViewModel.repositoryData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        repositoryViewModel.fetchRepository()
    }
    
    LazyColumn {
        items(repositories.itemCount) { index ->
            val repository = repositories[index]
            if (repository != null) {
                Text(text = repository.name, modifier = Modifier.padding(dp_16))
            } else {
                // Placeholder item while loading or if an item is null
                Text(text = "Loading...", modifier = Modifier.padding(dp_16))
            }

            // When reaching the last visible item, load the next page
            if (index == repositories.itemCount - 1) {
                repositoryViewModel.loadNextPage()
            }
        }
    }
}*/
@Composable
fun RepositoryList(repositoryViewModel: GitHubViewModel) {
    val repositories: LazyPagingItems<DataResponseItem> = repositoryViewModel.repositoryData.collectAsLazyPagingItems()

    LazyColumn {
        items(repositories.itemCount) { index ->
            val repository = repositories[index]
            if (repository != null) {
                Text(text = repository.name, modifier = Modifier.padding(dp_16))
            } else {
                // Placeholder item while loading or if an item is null
                Text(text = "Loading...", modifier = Modifier.padding(dp_16))
            }

            // When reaching the last visible item, load the next page
            if (index == repositories.itemCount - 1) {
                repositoryViewModel.loadNextPage()
            }
        }
    }
}
