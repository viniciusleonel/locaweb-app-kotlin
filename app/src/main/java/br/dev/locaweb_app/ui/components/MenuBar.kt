package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MenuBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ThemeViewModel
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val themeViewModel: ThemeViewModel = viewModel()
    val isDarkTheme = themeViewModel.isDarkTheme.value
    val regularColor = if (isDarkTheme) Color.White else Color.Black
    val navBarColor = viewModel.navBarColor.value

    if (currentRoute != "login" && currentRoute != "register") {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            MenuItem(
                icon = Icons.Filled.Email,
                text = "Emails",
                modifier = Modifier.weight(1f),
                color = if (currentRoute == "sent-emails" || currentRoute == "received-emails") navBarColor else regularColor,
                onClick = {
                    navController.navigate("sent-emails")
                }
            )
            MenuItem(
                icon = Icons.Filled.Add,
                text = "Send",
                modifier = Modifier.weight(1f),
                color = if (currentRoute == "send-email") navBarColor else regularColor, onClick = {
                    navController.navigate("send-email")
                }
            )
            MenuItem(
                icon = Icons.Filled.Settings,
                text = "Settings",
                modifier = Modifier.weight(1f),
                color = if (currentRoute == "settings") navBarColor else regularColor,
                onClick = {
                    navController.navigate("settings")
                }
            )
        }
    }
}