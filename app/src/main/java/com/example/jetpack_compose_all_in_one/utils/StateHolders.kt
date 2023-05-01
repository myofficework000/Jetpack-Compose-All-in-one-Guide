package com.example.jetpack_compose_all_in_one.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList


// When calling onItemsExhausted, the pointer will reset to 0 no matter what.
//      Modify this list for fresh items. Or do nothing if you want it to
//      just cycle back to the first item.
class InfiniteList<T>(
    itemsInput: List<T> = listOf(),
    private val onItemsExhausted: (SnapshotStateList<T>) -> Unit = {}
){
    private var currentIndex = 0
    private var items = mutableStateListOf<T>()

    init{ items.addAll(itemsInput) }

    val current get() = items.getOrNull(currentIndex)
    val next get() = items.getOrNull(currentIndex+1)
    val size get() = items.size

    fun moveToNext(): Int {
        if (currentIndex >= items.size - 1) {
            onItemsExhausted(items)
            currentIndex = 0
        } else { currentIndex++ }
        return currentIndex
    }

    fun addAll(elements: Collection<T>) { items.addAll(elements) }

    operator fun get(ix: Int) = items.getOrNull(ix)
}