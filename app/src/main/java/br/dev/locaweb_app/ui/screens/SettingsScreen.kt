package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.theme.ButtonColors
import br.dev.locaweb_app.ui.theme.ShapeButton


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    Column {
        CustomButton(
            onClick = {
                navController.navigate("edit-profile")
            },
            colorsList = ButtonColors,
            text = "Edit Profile"
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            onClick = {
                navController.navigate("theme")
            },
            colorsList = ButtonColors,
            text = "Change Theme"
        )
    }
}
