package br.dev.locaweb_app.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.dev.locaweb_app.ui.theme.ButtonColorsWarning
import br.dev.locaweb_app.ui.theme.ShapeButton

@Composable
fun DeleteDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    buttonColors: List<Color>? = null
    ) {

    var showDialog by remember { mutableStateOf(dialogState) }

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text(text = "Delete Account?")
            },
            text = {
                Text("This action is irreversible!")
            },
            confirmButton = {
                CustomButton(
                    onClick = {
                        onConfirmRequest()
                    },
                    cornerShape = ShapeButton.medium,
                    colorsList = buttonColors,
                    text = "Confirm"
                )
            },
            dismissButton = {
                CustomButton(
                    onClick = {
                        onDismissRequest()
                    },
                    cornerShape = ShapeButton.medium,
                    colorsList = ButtonColorsWarning,
                    text = "Cancel"
                )
            }
        )
    }
}