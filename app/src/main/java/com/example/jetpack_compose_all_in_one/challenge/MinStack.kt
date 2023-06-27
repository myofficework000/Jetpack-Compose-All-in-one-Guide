package com.example.jetpack_compose_all_in_one.challenge


interface MinStack<T> {
    fun pop()
    fun push(element: T)
    fun top(): T
    fun min(): T
    fun getItem(index: Int): T
    val size: Int
    fun isEmpty(): Boolean
}