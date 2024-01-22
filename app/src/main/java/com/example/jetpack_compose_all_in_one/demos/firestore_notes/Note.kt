package com.example.jetpack_compose_all_in_one.demos.firestore_notes

data class Note (val title: String = "", val content: String = "") {
    constructor() : this("", "")
}