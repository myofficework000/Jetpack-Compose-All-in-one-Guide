package com.example.jetpack_compose_all_in_one.features.shared_pref.demo1

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class Demo1ViewModel: ViewModel() {
    private lateinit var encPrefs:  SharedPreferences

    fun savePrefSecurely(context: Context, email: String, password: String) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        encPrefs = EncryptedSharedPreferences.create(
            context,
            Constants.ENCRYPTED_PREF_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val editor = encPrefs.edit()
        editor.putString(Constants.EMAIL, email).apply()
        editor.putString(Constants.PASSWORD, password).apply()
    }
}