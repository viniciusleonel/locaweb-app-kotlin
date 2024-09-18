package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.dev.locaweb_app.ui.theme.ButtonColorsWarning

@Composable
fun ShowColorPicker(viewModel: ThemeViewModel, buttonColors: List<Color>? = null) {
    var showDialog by remember { mutableStateOf(false) }
    CustomButton(
        onClick = { showDialog = true },
        text = "Open Color Picker",
        colorsList = buttonColors
    )

    if (showDialog) {
        ColorPickerDialog(
            onDismiss = { showDialog = false },
            viewModel = viewModel,
            buttonColors = buttonColors
        )
    }
}


@Composable
fun ColorPickerDialog(
    viewModel: ThemeViewModel,
    onDismiss: () -> Unit,
    buttonColors: List<Color>? = null
) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(1f) }
    var brightness by remember { mutableStateOf(1f) }

    val color = Color.hsv(hue, saturation, brightness)

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SaturationBrightnessPicker(
                    hue = hue,
                    saturation = saturation,
                    brightness = brightness
                ) { s, b ->
                    saturation = s
                    brightness = b
                }
                HueSlider(hue = hue) { hueSelected ->
                    hue = hueSelected
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RectangleShape)
                        .background(color)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    onClick = {
                        viewModel.updateColor(color)
                        onDismiss()
                    },
                    text = "Save Color",
                    colorsList = buttonColors,
                )
                Spacer(modifier = Modifier.height(10.dp))

                CustomButton(
                    onClick = { onDismiss() },
                    text = "Close",
                    colorsList = ButtonColorsWarning,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HueSlider(hue: Float, onHueChange: (Float) -> Unit) {
    val gradient = Brush.horizontalGradient(
        0f to Color.Red,
        0.16f to Color.Yellow,
        0.33f to Color.Green,
        0.5f to Color.Cyan,
        0.66f to Color.Blue,
        0.83f to Color.Magenta,
        1f to Color.Red
    )

    Slider(
        value = hue,
        onValueChange = onHueChange,
        valueRange = 0f..360f,
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient),

        )
}

@Composable
fun SaturationBrightnessPicker(
    hue: Float,
    saturation: Float,
    brightness: Float,
    onSaturationBrightnessChange: (Float, Float) -> Unit
) {
    var touchOffset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val s = (offset.x / size.width).coerceIn(0f, 1f)
                    val b = 1f - (offset.y / size.height).coerceIn(0f, 1f)
                    touchOffset = offset
                    onSaturationBrightnessChange(s, b)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val colorStart = Color.White
            val colorEnd = Color.hsv(hue, 1f, 1f)

            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(colorStart, colorEnd),
                    startX = 0f,
                    endX = size.width
                )
            )

            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = 0f,
                    endY = size.height
                ),
                size = Size(size.width, size.height)
            )

            drawCircle(
                color = Color.Transparent,
                radius = 10.dp.toPx(),
                center = touchOffset,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = Color.White,
                radius = 12.dp.toPx(),
                center = touchOffset,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}
