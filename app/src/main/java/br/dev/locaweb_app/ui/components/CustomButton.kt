package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.RegularShape

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color? = null,
    colorsList: List<Color>? = null,
    cornerShape: CornerBasedShape? = null,
    themeViewModel: ThemeViewModel? = null
) {
    val isDarkTheme = themeViewModel?.isDarkTheme?.value
    val usersColor = themeViewModel?.navBarColor?.value ?: OceanBlue

    val brush = when {
        colorsList != null -> Brush.horizontalGradient(colorsList)
        color != null -> Brush.linearGradient(listOf(color, color))
        else -> Brush.linearGradient(listOf(usersColor, usersColor))
    }

    val textColor = if(isDarkTheme == true && colorsList == null && color == null) Color.Black else Color.White

    Button(
        onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
            .background(
                brush = brush,
                shape = cornerShape ?: RegularShape
            ),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color =  textColor,
            fontWeight = FontWeight.Bold
        )
    }
}
