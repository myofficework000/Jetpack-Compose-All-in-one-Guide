package com.example.jetpack_compose_all_in_one.features.chatmodule

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.utils.FcmRegisterService
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    db: FirebaseDatabase?
) : ViewModel() {
    val chatHistory = mutableStateListOf<ChatHistoryItem>()
    private val userRef = db?.getReference("user")

    init {
        userRef?.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val messageId = snapshot.key
                val data = snapshot.getValue<ChatHistoryItem>()
                data?.let {
                    chatHistory.add(it.copy(messageId = messageId))
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val messageId = snapshot.key
                val data = snapshot.getValue<ChatHistoryItem>()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                chatHistory.removeIf { it.messageId == snapshot.key }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    // messageId should be null here. It'll also be auto-generated here as well.
    fun sendMessage(
        data: ChatHistoryItem
    ) = userRef?.let {
        val messageId = it.push().key.toString()
        it.child(messageId).setValue(data).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewModelScope.launch(Dispatchers.IO) {
                    FcmRegisterService.sendMessage(DUMMY_TOKEN, data.message)
                }
            }
        }
    }

    companion object {
        const val DUMMY_TOKEN =
            "fTs5OlFQTWWs70Bw9fI47P:APA91bEQlFV9GRtBI1MuD9wOisizBHCFeHfsMbbjlUDAi0KFDwXO241QBbOTQkdsbBgJV7mxF_1rCDGinz4R2Xzhiak4z0fO5OC9KX6PGz7o2dP4sYxtrciWe2f98--rJ91s_9aBrCBR"
    }
}