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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.PAGER_BACKGROUND
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.coroutines.launch

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
    dotTint: Color = Color.Unspecified,
    currentPage: MutableState<Int> = rememberSaveable { mutableIntStateOf(0) },
    content: @Composable (PaddingValues) -> Unit
) {
    val boxHeight = rememberSaveable { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val pageSize = pageCount ?: pages.size

    content(PaddingValues(top = boxHeight.intValue.dp))

    if (pageSize > 1) {
        Box(
            modifier.then(Modifier.onGloballyPositioned {
                with(density) { boxHeight.intValue = it.size.height.toDp().value.toInt() }
            }),
            contentAlignment = Alignment.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SimpleTextButton(
                    stringResource(id = R.string.prev),
                    enabled = currentPage.value > 0,
                    fontWeight = FontWeight.Bold
                ) {
                    if (currentPage.value < cleanupPerPage.size) cleanupPerPage[currentPage.value]()
                    cleanupAllPages()
                    currentPage.value--
                    if (currentPage.value < pages.size) pages[currentPage.value]()
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until pageSize) {
                        if (i == currentPage.value)
                            Icon(
                                painterResource(R.drawable.baseline_dot_12), "",
                                Modifier.size(14.dp),
                                tint = dotTint
                            )
                        else Icon(
                            painterResource(R.drawable.outline_dot_12), "",
                            tint = dotTint
                        )
                    }
                }

                SimpleTextButton(
                    stringResource(id = R.string.next),
                    enabled = currentPage.value < pageSize - 1,
                    fontWeight = FontWeight.Bold
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

// If the into content starts with "http", for now it'll be considered
//      a url, and shows a web view instead.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagedLessonHeader(
    modifier: Modifier = Modifier,
    currentPage: Int,
    headers: List<String>,
    infoContent: List<String> = listOf()
) {
    var sheetOpened by remember{ mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    val webViewState = rememberWebViewState(
        infoContent.getOrNull(currentPage)?.takeIf { it.startsWith("http") } ?: ""
    )
    var isWebViewFinishedLoading by remember{ mutableStateOf(false) }

    LaunchedEffect(webViewState.loadingState) {
        isWebViewFinishedLoading = webViewState.loadingState == LoadingState.Finished
    }

    if (sheetOpened) {
        ModalBottomSheet(
            onDismissRequest = { sheetOpened = false },
            sheetState = sheetState
        ) {
            Box(
                Modifier
                    .padding(horizontal = dp_16)
                    .padding(bottom = 56.dp)) {
                with (infoContent.getOrElse(currentPage) { "No info" }) {
                    if (startsWith("http")) {
                        Box(
                            Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) { CircularProgressIndicator() }

                        WebView(state = webViewState)
                    }else Text(this)
                }
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        LessonHeader(
            text = headers.getOrElse(currentPage) { "Page ${currentPage + 1}" },
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )

        SimpleIconButton(
            iconResourceInt = R.drawable.outline_info_24,
            modifier = Modifier.align(Alignment.CenterEnd),
            iconModifier = Modifier.size(64.dp)
        ) {
            scope.launch {
                sheetOpened = true
                sheetState.show()
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