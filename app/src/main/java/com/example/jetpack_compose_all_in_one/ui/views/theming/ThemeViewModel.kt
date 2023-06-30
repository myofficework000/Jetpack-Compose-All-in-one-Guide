package com.example.jetpack_compose_all_in_one.ui.views.theming

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.utils.preferences.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repo: PreferenceRepository
): ViewModel() {
    // This should be immediately revert to false after setting it to true.
    //      It's merely a signal to the composable that the dialog needs to be opened.
    var openDialog by mutableStateOf(false)
        private set

    var themeMode by mutableStateOf(Themes.DEFAULT)
        private set

    init {
        themeMode = when(repo.getBoolean(DARK_THEME_PREF)) {
            null -> Themes.DEFAULT
            false -> Themes.LIGHT
            true -> Themes.DARK
        }
    }

    fun setTheme(result: Themes) {
        when (result) {
            Themes.DEFAULT -> repo.removePref(DARK_THEME_PREF)
            Themes.LIGHT -> repo.setBoolean(DARK_THEME_PREF, false)
            Themes.DARK -> repo.setBoolean(DARK_THEME_PREF, true)
        }

        themeMode = result
    }

    // This is the button request to open the dialog
    fun requestDialog() { openDialog = true }
    // This is what runs after the composable holding the dialog receives the request.
    fun dialogOpened() { openDialog = false }

    companion object {
        const val DARK_THEME_PREF = "isDark"
    }
}