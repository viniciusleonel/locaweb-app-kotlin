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


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    Column {
        Button(
            onClick = {
                navController.navigate("emails")
            },
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Settings")
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun SettingsPrev() {
//    SettingsScreen()
//}