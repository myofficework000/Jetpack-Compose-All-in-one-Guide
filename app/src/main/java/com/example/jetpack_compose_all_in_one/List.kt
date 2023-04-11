package com.example.jetpack_compose_all_in_one

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpack_compose_all_in_one.data.Country
import com.example.jetpack_compose_all_in_one.ui.theme.Dimens.dp_10

@Composable
fun HorizontalSimpleList(list: List<Country>){
    LazyRow(
        modifier = Modifier.wrapContentHeight()
    ){
        items(list){item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(dp_10))
        }
    }
}

@Composable
fun VerticalSimpleList(list: List<Country>){
    LazyColumn(
        modifier = Modifier.wrapContentHeight()
    ){
        items(list){item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(dp_10))
        }
    }
}


@Composable
fun SimpleVerticalGridList(list: List<Country>){
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.wrapContentHeight()
    ){
        items(list){item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(dp_10))
        }
    }
}