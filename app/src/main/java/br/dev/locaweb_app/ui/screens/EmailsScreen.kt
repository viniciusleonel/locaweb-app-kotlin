package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun EmailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null,
    themeViewModel: ThemeViewModel
) {

    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

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
