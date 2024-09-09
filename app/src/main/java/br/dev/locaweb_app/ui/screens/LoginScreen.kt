package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
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

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isErrorUsername by remember {
        mutableStateOf(false)
    }
    var isErrorPassword by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomInput(
            textInput = username,
            onValueChange = { letter ->
                username = letter
            },
            label = "Insert your username:",
            placeholder = "Insira seu username:",
            icon = Icons.Filled.Person,
            color = OceanBlue,
            cornerShape = ShapeButton.medium,
            isError = isErrorUsername
        )
        if (isErrorUsername) ErrorMessage(text = "Username é obrigatório!")
        CustomInput(
            textInput = password,
            onValueChange = { letter ->
                password = letter
            },
            label = "Insert your password:",
            placeholder = "Insira sua senha:",
            icon = Icons.Filled.Lock,
            keyboard = KeyboardType.Password,
            isPassword = true,
            color = OceanBlue,
            cornerShape = ShapeButton.medium,
            isError = isErrorPassword
        )
        if (isErrorPassword) ErrorMessage(text = "Senha é obrigatório!")
        CustomButton(
            onClick = {
//                navController?.navigate("profile")
                if (username.isEmpty()) isErrorUsername = true else isErrorUsername = false
                if (password.isEmpty()) isErrorPassword = true else isErrorPassword = false
            },
            colorsList = buttonColors,
            text = "Login",
            cornerShape = ShapeButton.medium
        )
    }
}

val buttonColors = listOf(OceanBlue, Color.Blue, OceanBlue)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}