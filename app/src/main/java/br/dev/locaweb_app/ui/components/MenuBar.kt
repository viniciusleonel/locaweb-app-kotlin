package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, // Espaço entre os itens
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // Garante que a Row ocupe toda a largura
            .height(80.dp) // Altura fixa do traço
    ) {
        MenuItem(
            icon = Icons.Filled.Settings,
            text = "Settings",
            modifier = Modifier.weight(1f), // Faz com que o MenuItem ocupe toda a largura disponível
            onClick = {
                // Ação a ser executada quando o botão for clicado
                println("Settings button clicked")
            }
        )
        MenuItem(
            icon = Icons.Filled.Email,
            text = "Emails",
            modifier = Modifier.weight(1f),
            onClick = {
                // Ação a ser executada quando o botão for clicado
                println("Emails button clicked")
            }
        )
        MenuItem(
            icon = Icons.Filled.Person,
            text = "Profile",
            modifier = Modifier
                .weight(1f),
            onClick = {
                // Ação a ser executada quando o botão for clicado
                println("Profile button clicked")
            }
        )
    }
}