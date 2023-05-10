package com.example.jetpack_compose_all_in_one.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.PAGER_BACKGROUND
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun bitmapDescriptorFromRes(
    context: Context,
    redId: Int,
    width: Dp? = null,
    height: Dp? = null
) = ContextCompat.getDrawable(context, redId)?.run {
    val w = width?.value?.toInt() ?: intrinsicWidth
    val h = height?.value?.toInt() ?: intrinsicHeight

    setBounds(0, 0, w, h)
    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    draw(Canvas(bitmap))
    BitmapDescriptorFactory.fromBitmap(bitmap)
}

// cleanupPerPage runs first, then cleanupAllPage
@Composable
fun LogicPager(
    modifier: Modifier = Modifier,
    pages: List<() -> Unit> = listOf(),
    cleanupPerPage: List<() -> Unit> = listOf(),
    cleanupAllPages: () -> Unit = {},
    pageCount: Int? = null,
    currentPage: MutableState<Int> = rememberSaveable { mutableStateOf(0) },
    content: @Composable (PaddingValues) -> Unit
) {
    val boxHeight = rememberSaveable { mutableStateOf(0) }
    val density = LocalDensity.current
    val pageSize = pageCount ?: pages.size

    content(PaddingValues(top = boxHeight.value.dp))

    if (pageSize > 1) {
        Box(
            modifier.then(Modifier.onGloballyPositioned {
                with(density) { boxHeight.value = it.size.height.toDp().value.toInt() }
            }),
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleTextButton(
                    stringResource(id = R.string.prev),
                    enabled = currentPage.value > 0
                ) {
                    if (currentPage.value < cleanupPerPage.size) cleanupPerPage[currentPage.value]()
                    cleanupAllPages()
                    currentPage.value--
                    if (currentPage.value < pages.size) pages[currentPage.value]()
                }

                Row {
                    for (i in 0 until pageSize) {
                        if (i == currentPage.value) Text("X") else Text("O")
                    }
                }

                SimpleTextButton(
                    stringResource(id = R.string.next),
                    enabled = currentPage.value < pageSize - 1
                ) {
                    if (currentPage.value < cleanupPerPage.size) cleanupPerPage[currentPage.value]()
                    cleanupAllPages()
                    currentPage.value++
                    if (currentPage.value < pages.size) pages[currentPage.value]()
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginPagerPreview() {
    //The regular preview doesn't show, but once it goes into interactive mode,
    //the page will get the padding.
    LogicPager(
        Modifier.fillMaxWidth(),
        listOf(
            {},
            {},
            {}
        )
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .background(PAGER_BACKGROUND)
        ) {}
    }
}