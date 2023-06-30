package com.example.jetpack_compose_all_in_one.utils.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
): PreferenceRepository {
    private val edit = prefs.edit()

    override fun removePref(name: String) { edit.remove(name).apply() }

    override fun getString(name: String): String? = prefs.getString(name, null)
    override fun setString(name: String, value: String) {
        edit.putString(name, value).apply()
    }

    override fun getBoolean(name: String): Boolean? =
        if (prefs.contains(name)) prefs.getBoolean(name, false) else null

    override fun setBoolean(name: String, value: Boolean) {
        edit.putBoolean(name, value).apply()
    }
}

interface PreferenceRepository {
    fun removePref(name: String)

    fun getString(name: String): String?
    fun setString(name: String, value: String)

    fun getBoolean(name: String): Boolean?
    fun setBoolean(name: String, value: Boolean)
}