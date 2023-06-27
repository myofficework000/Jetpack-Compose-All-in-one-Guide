package com.example.jetpack_compose_all_in_one.features.shared_pref.demo1

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.jetpack_compose_all_in_one.features.shared_pref.demo1.Constants.EMAIL
import com.example.jetpack_compose_all_in_one.features.shared_pref.demo1.Constants.ENCRYPTED_PREF_FILE_NAME
import com.example.jetpack_compose_all_in_one.features.shared_pref.demo1.Constants.PASSWORD

fun savePref(context: Context, email: String, password: String) {

}

fun savePrefSecurely(context: Context, email: String, password: String) {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val encPrefs = EncryptedSharedPreferences.create(
        context,
        ENCRYPTED_PREF_FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    val editor = encPrefs.edit()
    editor.putString(EMAIL, email).apply()
    editor.putString(PASSWORD, password).apply()
}
