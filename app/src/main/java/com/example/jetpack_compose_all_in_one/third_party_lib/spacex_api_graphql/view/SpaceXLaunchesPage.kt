package com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.viewmodel.SpaceXLaunchesViewModel

@Composable
fun SpaceXLaunchesPage(
    vm: SpaceXLaunchesViewModel
) {
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(vm.launches) {
            LaunchCard(item = it)
        }
    }
}
/*

@Composable
fun LaunchesListPreview(
    launches: List<GetLaunchesPastQuery.LaunchesPast>
) {
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(launches) {
            LaunchCardPreview(item = it)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewLaunchesList() {
    LaunchesListPreview(launches = listOf(
        LaunchesResponse.empty.copy(
            missionName = "ABC",
            rocket = Rocket("","DEF"),
            launchSite = LaunchSite("","123",""),
            launchDateLocal = "2069-12-32"
        ),
        LaunchesResponse.empty.copy(
            missionName = "123",
            rocket = Rocket("","456"),
            launchSite = LaunchSite("","abc",""),
            launchDateLocal = "2042-00-01"
        )
    ))
}*/
