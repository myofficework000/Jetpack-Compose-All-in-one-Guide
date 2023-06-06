package com.example.jetpack_compose_all_in_one.lessons.lesson_9

object CounterHelper {

    fun processInput(editedText: String): String {
        return try {
            val counterValue = editedText.toInt()
            "Counter = $counterValue"
        } catch (e: NumberFormatException) {
            "Invalid entry"
        }
    }
}