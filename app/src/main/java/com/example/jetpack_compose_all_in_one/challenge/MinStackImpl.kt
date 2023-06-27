package com.example.jetpack_compose_all_in_one.challenge

class MinStackImpl<T : Any> : MinStack<Int> {

    val array = ArrayList<Int>()

    override val size: Int
        get() = array.size

    override fun getItem(index: Int) = array[index]

    override fun min(): Int = array.min()

    override fun top(): Int {
        return if (isEmpty()) 0
        else array.last()
    }

    override fun pop() {
        if (!isEmpty()) {
            array.drop(array.size)
        }
    }

    override fun push(element: Int) {
        array.add(element)
    }

    override fun isEmpty(): Boolean {
        return size > 0
    }
}

fun main() {

    val minStack = MinStackImpl<Int>()

    minStack.push(-2)

    minStack.push(0)

    minStack.push(-3)

    minStack.min()        // --> Returns -3.

    minStack.pop()

    minStack.top()        // --> Returns 0.

    minStack.min()        // --> Returns -2

    println(minStack.min())
    println( minStack.top() )
    println(minStack.min() )
}