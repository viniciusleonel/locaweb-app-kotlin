package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.ShapeButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = OceanBlue
    )

    Column (
       modifier = Modifier
           .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CustomInput(
            label = "Insert your username:",
            placeholder = "Insert your username:",
            icon = Icons.Filled.Person,
            color = OceanBlue,
            cornerShape = ShapeButton.medium
        )
        CustomInput(
            label = "Insert your password:",
            placeholder = "Insert your password:",
            icon = Icons.Filled.Lock,
            keyboard = KeyboardType.Password,
            isPassword = true,
            color = OceanBlue,
            cornerShape = ShapeButton.medium
        )
        CustomButton(
            onClick = {
                navController?.navigate("profile")
            },
            colorsList = buttonColors,
            text = "Login",
            cornerShape = ShapeButton.medium
        )
    }
}

val buttonColors = listOf(OceanBlue, Color.Blue,  OceanBlue)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}