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
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    Column {
        Button(
            onClick = {
                navController.navigate("settings")
            },
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Profile")
        }
    }
}