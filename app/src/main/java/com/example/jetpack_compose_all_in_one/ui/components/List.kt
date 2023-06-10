package com.example.jetpack_compose_all_in_one.ui.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.lessons.lesson_8.ShimmerCard
import com.example.jetpack_compose_all_in_one.ui.theme.LightBlue
import com.example.jetpack_compose_all_in_one.ui.theme.Pink80
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_3
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16

@Composable
fun HorizontalSimpleList(list: List<Country>) {
    LazyRow(
        modifier = Modifier.wrapContentHeight()
    ) {
        items(list) { item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(dp_10))
        }
    }
}

@Composable
fun VerticalSimpleList(list: List<Country>) {
    LazyColumn(
        modifier = Modifier.wrapContentHeight()
    ) {
        items(list) { item ->
            Text(text = item.name, fontWeight = FontWeight.Bold)
            CustomSpacer(height = dp_3)
        }
    }
}


@Composable
fun SimpleVerticalGridList(list: List<String>, numOfColumns: Int = 3) {
    LazyVerticalGrid(
        GridCells.Fixed(numOfColumns),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dp_15)
    ) {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .background(color = LightBlue)
                    .height(dp_50)
                    .padding(dp_15)
            ) {
                Text(text = item, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SimpleHorizontalGridList(list: List<String>, numOfRows: Int = 3) {
    LazyHorizontalGrid(
        GridCells.Fixed(numOfRows),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dp_15)
    ) {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .padding(dp_15)
                    .background(color = Pink80)
                    .size(dp_50)
            ) {
                Text(text = item, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CustomVerticalList(list: List<Country>) {
    val listOfItem = remember { mutableStateListOf<Country>() }
    var openDialog by remember { mutableStateOf(false) }
    var position = 0
    list.forEach {
        listOfItem.add(it)
    }
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        itemsIndexed(listOfItem) { index, item ->
            CardTextWithIcon(item, onRemoveClicked = {
                openDialog = true
                position = index
            })
            if (openDialog) {
                DeleteDialog(onConfirmClicked = {
                    openDialog = false
                    listOfItem.removeAt(position)
                })
            }
        }
    }
}

@Composable
fun ExpandableCardList(items: List<Country>) {
    LazyColumn {
        items(items) { item ->
            ExpandableCard(item = item)
            Divider()
        }
    }
}

@Composable
fun ExpandableCard(item: Country) {
    val expandedState = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dp_15)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp_15),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = sp_16,
                    modifier = Modifier.align(CenterVertically)
                )
                IconButton(onClick = { expandedState.value = !expandedState.value }) {
                    if (expandedState.value) {
                        Icon(
                            imageVector = Icons.Filled.ExpandLess,
                            contentDescription = "Click to close"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription = "Click to view more"
                        )
                    }
                }
            }
            if (expandedState.value) {
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
            }
        }
    }
}

@Composable
fun ExpandableAnimatedList(items: List<Country>) {
    LazyColumn {
        items(items) { item ->
            AnimatedExpandableCard(item = item)
            Divider()

        }
    }
}

@Composable
fun AnimatedExpandableCard(item: Country) {
    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dp_15)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .clickable { expanded.value = !expanded.value },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp_15),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = sp_16,
                    modifier = Modifier.align(CenterVertically)
                )
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    if (expanded.value) {
                        Icon(
                            imageVector = Icons.Filled.ExpandLess,
                            contentDescription = "Click to close"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription = "Click to view more"
                        )
                    }
                }
            }
            if (expanded.value) {
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
                Text(
                    text = stringResource(id = R.string.additional_info_for_expandable_list)
                )
            }
        }
    }
}

@Composable
fun ShimmerAnimatedList() {
    // Your screen content goes here
    LazyColumn(Modifier.background(getRandomColor())) {
        items(10) {
            ShimmerCard()
        }
    }
}

data class Country(val name: String)

fun getCountries() = listOf(
    Country("USA"),
    Country("UK"),
    Country("India"),
    Country("Vietnam"),
    Country("Korea"),
    Country("Mexico"),
    Country("Germany"),
    Country("Canada"),
    Country("Australia"),
    Country("Thailand"),
    Country("Singapore"),
    Country("China")
)

