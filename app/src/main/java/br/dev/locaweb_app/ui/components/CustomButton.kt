package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.locaweb_app.ui.theme.LakeBlue
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.ShapeButton

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    color: Color? = null,
    colorsList: List<Color>? = null,
    cornerShape: CornerBasedShape,
    modifier: Modifier = Modifier
) {
    val brush = when {
        colorsList != null -> Brush.horizontalGradient(colorsList)
        color != null -> Brush.linearGradient(listOf(color, color))
        else -> Brush.linearGradient(listOf(Color.Black, Color.Black)) // Cor padrão
    }

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
            .padding(20.dp)
            .background(
                brush,
                shape = cornerShape
            ),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

val colors = listOf(
    OceanBlue,
    LakeBlue,
    OceanBlue
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ButtonPrev() {
    Column {
        CustomButton(onClick = {}, text = "Custom", colorsList = listOf(OceanBlue, LakeBlue), cornerShape = ShapeButton.medium)
        CustomButton(onClick = {}, text = "Single Color", color = OceanBlue, cornerShape = ShapeButton.medium)
        CustomButton(onClick = {}, text = "Default Color", cornerShape = ShapeButton.medium)
    }
}