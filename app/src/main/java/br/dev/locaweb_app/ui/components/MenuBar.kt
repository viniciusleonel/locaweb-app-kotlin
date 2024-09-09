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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.dev.locaweb_app.ui.theme.Blue

@Composable
fun MenuBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != "login") {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            MenuItem(
                icon = Icons.Filled.Settings,
                text = "Settings",
                modifier = Modifier.weight(1f), // Faz com que o MenuItem ocupe toda a largura disponível
                color = if (currentRoute == "settings") Blue else Color.Black,
                onClick = {
                    navController.navigate("settings")
                }
            )
            MenuItem(
                icon = Icons.Filled.Email,
                text = "Emails",
                modifier = Modifier.weight(1f),
                color = if (currentRoute == "emails") Blue else Color.Black,
                onClick = {
                    navController.navigate("emails")
                }
            )
            MenuItem(
                icon = Icons.Filled.Person,
                text = "Profile",
                modifier = Modifier
                    .weight(1f),
                color = if (currentRoute == "profile") Blue else Color.Black,
                onClick = {
                    navController.navigate("profile")
                }
            )
        }
    }
}