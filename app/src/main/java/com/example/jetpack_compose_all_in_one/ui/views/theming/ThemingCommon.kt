package com.example.jetpack_compose_all_in_one.ui.views.theming

enum class Themes {
    DEFAULT,
    LIGHT,
    DARK
}

// I won't bother checking for out of bound exceptions.
// Just use this carefully.
internal fun Int.ordinalToThemes() = Themes.values()[this]

// Change the returned value to R.string.xxx when the string resource is ready.
internal fun Array<Themes>.toLabel() = map { it.toLabel()}
internal fun Themes.toLabel() = when (this) {
    Themes.DEFAULT -> "System Default"
    Themes.LIGHT -> "Light"
    Themes.DARK -> "Dark"
}