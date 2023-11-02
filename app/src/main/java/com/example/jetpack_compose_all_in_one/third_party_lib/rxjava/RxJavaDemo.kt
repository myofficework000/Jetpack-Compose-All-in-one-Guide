package com.example.jetpack_compose_all_in_one.third_party_lib.rxjava

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Composable
fun RxJavaDemo(
    vm: RxJavaDemoViewModel
) {
    val currentPage = rememberSaveable { mutableIntStateOf(0) }

    LogicPager(
        pageCount = 5,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.5f))
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentPage.intValue) {
                0 -> RxJavaDemoCompletable(vm)
                1 -> RxJavaDemoSingle(vm)
                2 -> RxJavaDemoMaybe(vm)
                3 -> RxJavaDemoObservable(vm)
                4 -> RxJavaDemoFlowable(vm)
                5-> DebounceSearchDemo()
                else -> {}
            }
        }
    }
}