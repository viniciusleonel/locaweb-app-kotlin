package br.dev.locaweb_app.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//val LakeBlue = Color(0xFF43C6AC)
val OceanBlue = Color(0xFF191654)
val Blue = Color(0xFF0835A5)
val Red = Color(0xFFdd1818)
val DarkRed = Color(0xFF333333)

val ButtonColors = listOf(OceanBlue, Color.Blue, OceanBlue)
val ButtonColorsWarning = listOf(DarkRed, Red, DarkRed)

fun darkenColor(color: Color, factor: Float): Color {
    val hsl = FloatArray(3)
    color.toHsl(hsl)
    hsl[2] = (hsl[2] * (1 - factor)).coerceIn(0f, 1f)

    return Color.hsv(hsl[0], hsl[1], hsl[2])
}

fun Color.toHsl(hsl: FloatArray) {
    val rgb = this.toArgb()
    android.graphics.Color.colorToHSV(rgb, hsl)
}