package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null,
    themeViewModel: ThemeViewModel,
) {

    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    Column (
        modifier = modifier
    ){
        CustomButton(
            onClick = {
                navController.navigate("edit-profile")
            },
            text = "Edit Profile",
            colorsList = buttonColors
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            onClick = {
                navController.navigate("theme")
            },
            text = "Change Theme",
            colorsList = buttonColors
        )
    }
}
