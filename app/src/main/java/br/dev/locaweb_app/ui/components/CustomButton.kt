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
    colorsList: List<Color>,
    cornerShape: CornerBasedShape,
    modifier: Modifier = Modifier) {
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(
                Brush.horizontalGradient(
                    colors = colorsList
                ),
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

@Preview(showSystemUi = true)
@Composable
private fun ButtonPrev() {
    CustomButton(onClick = {}, text = "Custom", colorsList = colors, cornerShape = ShapeButton.medium)
}