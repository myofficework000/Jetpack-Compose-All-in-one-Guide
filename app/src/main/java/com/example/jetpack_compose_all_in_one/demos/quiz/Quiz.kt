package com.example.jetpack_compose_all_in_one.demos.quiz

data class Quiz(
    val id:Int,
    val options:List<String>,
    val answer:String,
    val statement:String
)
