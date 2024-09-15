package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.dev.locaweb_app.ui.theme.Blue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    themeViewModel: ThemeViewModel
) {
    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    LaunchedEffect(usersColor) {
        systemUiController.setStatusBarColor(color = usersColor)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != "login" && currentRoute != "register") {

        systemUiController.setStatusBarColor(
            color = Blue
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = usersColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            if (currentRoute != null) {
                Text(
                    text = currentRoute.uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            } else {
                Text(
                    text = "Tittle",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "",
                tint = usersColor,
                modifier = Modifier
                    .padding(end = 16.dp)

            )
        }
    }
}
