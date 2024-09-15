package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ThemeViewModel


@Composable
fun EmailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null
) {
    Column {
        CustomButton(
            onClick = {
                navController.navigate("profile")
            },
            colorsList = buttonColors,
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "Emails"
        )
    }
}
