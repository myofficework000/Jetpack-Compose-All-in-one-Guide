package com.example.jetpack_compose_all_in_one.features.login_style_1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginStyle1ViewModel @Inject constructor(
    private val api: ApiLoginService,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val loginDetail = LoginStateHolder()

    fun login() {
        val loginBody = LoginRequest(
            loginDetail.email.value,
            loginDetail.password.value
        )

        viewModelScope.launch(ioDispatcher) {
            Log.i("tag", "Success")
            /*api.login(loginBody).apply {
                    body()?.let {
                        println(it.user?.username)
                    } ?: run {
                        errorBody()?.let {
                            println(it.string())
                        }
                    }
                }*/
        }
    }

    fun register(
        registerBody: RegisterRequest
    ) {
        viewModelScope.launch(ioDispatcher) {
            api.register(registerBody).apply {
                body()?.let {
                    println(it.user?.username)
                } ?: run {
                    errorBody()?.let {
                        println(it.string())
                    }
                }
            }
        }
    }

    fun validateEmail(email: String) {
        viewModelScope.launch(ioDispatcher) {
            api.validateEmail(email).apply {
                body()?.let {
                    println(it)
                } ?: run {
                    errorBody()?.let {
                        println(it.string())
                    }
                }
            }
        }
    }
}