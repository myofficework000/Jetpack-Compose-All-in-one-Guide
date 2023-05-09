package com.example.jetpack_compose_all_in_one.features.login_style_2

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginStyle2ViewModel(): ViewModel() {
    var email =  mutableStateOf("")
    var password =  mutableStateOf("")
    lateinit var firebaseUser: FirebaseUser
    var loginStatus = MutableLiveData<String>("")
    var registerStatus = MutableLiveData<String>("")

    fun login() {
        val firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser != null) {
            firebaseUser = firebaseAuth.currentUser!!
        }
        firebaseAuth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task->
                if(task.isSuccessful) {
                    loginStatus.postValue("Login Success")
                } else {
                    loginStatus.postValue("Login Failed")
                }
            }
//        viewModelScope.launch (Dispatchers.IO) {
//            delay(1000)
//            withContext(Dispatchers.Main) {
//                onSuccess()
//            }
//        }
    }
    fun register() {
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            firebaseUser = firebaseAuth.currentUser!!
        }
        firebaseAuth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task->
                if(task.isSuccessful) {
                    if (firebaseAuth.currentUser != null) {
                        firebaseUser = firebaseAuth.currentUser!!
                    }
                    registerStatus.postValue("Register Success")
                    sendVerificationEmailLink()
                } else {
                    registerStatus.postValue("Register Failed")
                }
            }
    }
    private fun sendVerificationEmailLink() {
        if(this::firebaseUser.isInitialized) {
            firebaseUser.sendEmailVerification().addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    registerStatus.postValue("Verification email sent!")
                } else {
                    registerStatus.postValue("Verification email failed!")
                }
            }
        }
    }
}