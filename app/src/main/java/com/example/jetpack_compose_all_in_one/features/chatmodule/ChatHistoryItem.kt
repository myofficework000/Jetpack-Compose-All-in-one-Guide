package com.example.jetpack_compose_all_in_one.features.chatmodule

import com.google.firebase.database.Exclude

// Per Firebase requirement, all variables must be optional.
data class ChatHistoryItem(
    val userId: String = "",
    val date: Long = 0,
    val message: String = "",
    val isRead: Boolean = false, // Create a new item if this is changed.
    val isCurrentUser: Boolean = false,
    // The ID is the key in Firebase, so it'll be added later via .copy()
    @Exclude val messageId: String? = null
)
