package com.example.jetpack_compose_all_in_one.lessons.lesson_21

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("User")
        val NAME_KEY = stringPreferencesKey("name")
        val EMAIL_KEY = stringPreferencesKey("email")
    }

    val getName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences [NAME_KEY] ?: "Empty"
        }

    suspend fun saveName(name : String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences [EMAIL_KEY] ?: "Empty"
        }

    suspend fun saveEmail(email : String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.remove(NAME_KEY)
            preferences.remove(EMAIL_KEY)
        }
    }

}