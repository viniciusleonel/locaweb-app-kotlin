package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.theme.ButtonColors
import br.dev.locaweb_app.ui.theme.ShapeButton

@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ThemeViewModel
) {
    val isDarkTheme = viewModel.isDarkTheme.value

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text("Escolha o tema:")
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            // Botão para alternar o tema
            CustomButton(
                onClick = { viewModel.toggleTheme() }, // Altera o tema aqui
                colorsList = ButtonColors,
                text = if (isDarkTheme) "Tema Claro" else "Tema Escuro", // Texto do botão muda conforme o tema
                cornerShape = ShapeButton.medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Tema atual: " + if (isDarkTheme) "Escuro" else "Claro")
        }
    }
}

