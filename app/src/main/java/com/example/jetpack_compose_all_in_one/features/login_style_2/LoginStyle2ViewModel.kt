package com.example.jetpack_compose_all_in_one.features.login_style_2

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginStyle2ViewModel: ViewModel() {
    var email =  mutableStateOf("")
    var password =  mutableStateOf("")
    var result = mutableStateOf("")

    fun login(onSucess:() -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            delay(1000)
            withContext(Dispatchers.Main) {
                onSucess()
            }
        }
    }
}