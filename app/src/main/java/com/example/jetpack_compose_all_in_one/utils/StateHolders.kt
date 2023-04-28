package com.example.jetpack_compose_all_in_one.utils


// When calling onItemsExhausted, the pointer will reset to 0 no matter what.
//      Modify this list for fresh items. Or do nothing if you want it to
//      just cycle back to the first item.
class InfiniteList<T>(
    itemsInput: List<T>,
    private val onItemsExhausted: (MutableList<T>) -> Unit = {}
){
    private var currentIndex = 0
    private var items: MutableList<T>
    init{ items = itemsInput.toMutableList() }

    val current = items[currentIndex]
    val next get() = items[currentIndex+1]
    val size get() = items.size

    fun moveToNext(): Int {
        if (currentIndex >= items.size - 1) {
            onItemsExhausted(items)
            currentIndex = 0
        } else { currentIndex++ }
        return currentIndex
    }

    operator fun get(ix: Int) = items.getOrNull(ix)
}