package com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.jetpack_compose_all_in_one.lessons.lesson_2.ImageDownloadWithGlideExample
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.data.Business
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.viewModel.YelpViewModel

@Composable
fun YelpUIScreen() {
// Naresh
    val viewModel = hiltViewModel<YelpViewModel>()

    val businessList by viewModel.businessList.collectAsState()
    
    LazyColumn() {
        items(businessList){
            BusinessRow(business = it)
        }
    }

}

@Composable
fun BusinessRow(business: Business){
    Row() {
        AsyncImage(modifier = Modifier.size(100.dp),model = business.url, contentDescription = "MoviePoster" )
        Column() {
            Text(text = business.name )
            Text(text = if (business.isClosed) "Closed" else "Open")
            Text(text = business.ratings.toString() )
        }
    }

}