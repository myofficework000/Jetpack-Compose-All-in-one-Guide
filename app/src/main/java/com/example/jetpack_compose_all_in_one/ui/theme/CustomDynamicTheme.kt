package com.example.jetpack_compose_all_in_one.ui.theme

import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DoNotInline
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * There's no easy way to customize dynamic background, so
 * this is the only choice: Replace the whole thing...
 */
@RequiresApi(Build.VERSION_CODES.S)
fun dynamicLightColorScheme(context: Context): ColorScheme = lightColorScheme(
        primary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_600),
        onPrimary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_0),
        primaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent1_100),
        onPrimaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent1_900),
        inversePrimary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_200),
        secondary = ColorResourceHelper.getColor(context, android.R.color.system_accent2_600),
        onSecondary = ColorResourceHelper.getColor(context, android.R.color.system_accent2_0),
        secondaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent2_100),
        onSecondaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent2_900),
        tertiary = ColorResourceHelper.getColor(context, android.R.color.system_accent3_600),
        onTertiary = ColorResourceHelper.getColor(context, android.R.color.system_accent3_0),
        tertiaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent3_100),
        onTertiaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent3_900),
        background = PAGER_BACKGROUND, //ColorResourceHelper.getColor(context, android.R.color.system_neutral1_10),
        onBackground = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_900),
        surface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_10),
        onSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_900),
        surfaceVariant = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_100),
        onSurfaceVariant = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_700),
        inverseSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_800),
        inverseOnSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_50),
        outline = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_500),
    )

@RequiresApi(Build.VERSION_CODES.S)
fun dynamicDarkColorScheme(context: Context): ColorScheme = darkColorScheme(
        primary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_200),
        onPrimary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_800),
        primaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent1_700),
        onPrimaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent1_100),
        inversePrimary = ColorResourceHelper.getColor(context, android.R.color.system_accent1_600),
        secondary = ColorResourceHelper.getColor(context, android.R.color.system_accent2_200),
        onSecondary = ColorResourceHelper.getColor(context, android.R.color.system_accent2_800),
        secondaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent2_700),
        onSecondaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent2_100),
        tertiary = ColorResourceHelper.getColor(context, android.R.color.system_accent3_200),
        onTertiary = ColorResourceHelper.getColor(context, android.R.color.system_accent3_800),
        tertiaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent3_700),
        onTertiaryContainer = ColorResourceHelper.getColor(context, android.R.color.system_accent3_100),
        background = PAGER_BACKGROUND, // ColorResourceHelper.getColor(context, android.R.color.system_neutral1_900),
        onBackground = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_100),
        surface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_900),
        onSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_100),
        surfaceVariant = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_700),
        onSurfaceVariant = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_200),
        inverseSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_100),
        inverseOnSurface = ColorResourceHelper.getColor(context, android.R.color.system_neutral1_800),
        outline = ColorResourceHelper.getColor(context, android.R.color.system_neutral2_400),
    )

private object ColorResourceHelper {
    @DoNotInline
    fun getColor(context: Context, @ColorRes id: Int): Color {
        return Color(context.resources.getColor(id, context.theme))
    }
}
