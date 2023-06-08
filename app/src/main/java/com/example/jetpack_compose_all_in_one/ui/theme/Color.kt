package com.example.jetpack_compose_all_in_one.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)

val Red500 = Color(0xFFF44336)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)

val Pink10 = Color(0xFFed4c78)
val Pink40 = Color(0xFF7D5260)
val Pink80 = Color(0xFFEFB8C8)

val Green700 = Color(0xFF4CAF50)
val Green100 = Color(0xFF8BC34A)

val Blue10 = Color(0xFF2a3b71)
val Blue20 = Color(0xFF445691)
val Blue30 = Color(0xFF0E3DCF)
val LightBlue = Color(0xFF8FA2DD)

val WhiteToBlue20 = Brush.horizontalGradient(listOf(Color.White, Blue20))
val Blue10ToBlue20 = Brush.horizontalGradient(listOf(Blue10, Blue20))
val LightBlueToBlue30 = Brush.linearGradient(listOf(LightBlue, Blue30))
val Pink10ToPink80 = Brush.linearGradient(listOf(Pink10, Pink80))

//For Chatting feature
val ShadowColor = Color(0xffA1A1A1)
val DefaultBubbleColor = Color.White
val DefaultBubbleColor2 = Color(0xffE7FFDB)
val SentMessageColor = Color(0xffE7FFDB)
val ReceivedMessageColor = Color.White
val DateColor = Color(0xffd4eaf4)

//val BackgroundColor = Color(0xffefe8df)
val BackgroundColor = Color(0xffFBE9E7)
val ChatTextColor = Color.Black
val ChatTimeColor = Color.Gray

val L1BoxColor1 = Color(0xFF40C9D8)
val L1BoxColor2 = Color(0xFF17A5F3)
val L1BoxColor3 = Color(0xFFF39D13)

var PAGER_BACKGROUND = Color(0xFFF39D13).copy(alpha = 0.5f)
val LESSON_HEADER_COLOR = Color.White

val RedA100 = Color(0xFFFF8A80)
val PinkA100 = Color(0xFFFF80AB)
val LavenderA100 = Color(0xFFEA80FC)
val VioletA100 = Color(0xFF8C9EFF)
val OceanA100 = Color(0xFF80D8FF)
val SkyA100 = Color(0xFF84FFFF)
val MintA100 = Color(0xFFA7FFEB)
val GreenA100 = Color(0xFFCCFF90)
val YellowA100 = Color(0xFFFFE57F)
val OrangeA100 = Color(0xFFFF9E80)

// For air quality bar
val AqiGradient = Brush.horizontalGradient(
    0.0f to Color(0xFF9AF878),
    0.1f to Color(0xFFFFF522),
    0.2f to Color(0xFFB2D806),
    0.3f to Color(0xFFCA2314),
    0.4f to Color(0xFF3565DF),
    0.5f to Color(0xFF0030C0),
    0.6f to Color(0xFF7C09FF),
    0.7f to Color(0xFF520600)
)

val orange200 = Color(0xFFff7961)
val orange500 = Color(0xFFf44336)
val orange700 = Color(0xFFba000d)

val teal200 = Color(0xff80deea)
val twitterColor = Color(0xFF1DA1F2)
val tiktokBlue = Color(0xFF69C9D0)
val tiktokRed = Color(0xFFEE1D52)
val tiktokBlack = Color(0xFF010101)
val blue = Color(0xFF5851DB)

val purple200 = Color(0xFFB39DDB)
val purple = Color(0xFF833AB4)
val purple700 = Color(0xFF512DA8)

val orange = Color(0xFFF56040)
val yellow = Color(0xFFFCAF45)

val graySurface = Color(0xFF2A2A2A)
val gradientGreenColors = listOf(Green100, Green700, GreenA100)
val gradientRedColors = listOf(orange, tiktokRed)
val gradientBluePurple = listOf(blue, purple)
val instagramGradient = listOf(blue, purple, orange, yellow)