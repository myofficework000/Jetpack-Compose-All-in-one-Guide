package com.example.jetpack_compose_all_in_one.features.login_style_2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginStyle2ViewModel() : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    lateinit var firebaseUser: FirebaseUser

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> = _registerStatus

    private val _verifyEmailStatus = MutableLiveData<String>()
    val verifyEmailStatus: LiveData<String> = _verifyEmailStatus

    fun login() {
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            firebaseUser = firebaseAuth.currentUser!!
        }
        firebaseAuth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Login Success"
                } else {
                    _loginStatus.value = "Login Failed"
                }
            }
    }

    fun register() {
        val firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            firebaseUser = firebaseAuth.currentUser!!
        }
        firebaseAuth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (firebaseAuth.currentUser != null) {
                        firebaseUser = firebaseAuth.currentUser!!
                    }
                    _registerStatus.value = "Register Success"
                    sendVerificationEmailLink()
                } else {
                    _registerStatus.value = "Register Failed"
                }
            }
    }

    private fun sendVerificationEmailLink() {
        if (this::firebaseUser.isInitialized) {
            firebaseUser.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _verifyEmailStatus.value = "Verification email sent!"
                } else {
                    _verifyEmailStatus.value = "Verification email failed!"
                }
            }
        }
    }
}