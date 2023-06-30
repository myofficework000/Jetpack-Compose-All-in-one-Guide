package com.example.jetpack_compose_all_in_one.ui.views.theming

import androidx.compose.runtime.Composable
import com.example.jetpack_compose_all_in_one.ui.components.ChoiceDialog

@Composable
fun ThemeSettingsDialog(
    vm: ThemeViewModel,
    onDismiss: () -> Unit
) {
    ChoiceDialog(
        currentChoice = vm.themeMode.ordinal,
        choices = Themes.values().toLabel(),
        onDismiss = onDismiss
    ) { vm.setTheme(it.ordinalToThemes()) }
}