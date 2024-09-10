package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.user.login
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.ShapeButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
//    userViewModel: UserViewModel
) {

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }
    var isErrorName by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorUsername by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorCheckPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomInput(
            textInput = name,
            onValueChange = { letter ->
                name = letter
            },
            label = "Insert your name:",
            placeholder = "Insira seu nome:",
            icon = Icons.Filled.Person,
            color = OceanBlue,
            cornerShape = ShapeButton.medium,
            isError = isErrorName
        )
        if (isErrorName) ErrorMessage(text = "Nome é obrigatório!")
        CustomInput(
            textInput = email,
            onValueChange = { letter ->
                email = letter
            },
            label = "Insert your email:",
            placeholder = "Insira seu email:",
            icon = Icons.Filled.Email,
            color = OceanBlue,
            cornerShape = ShapeButton.medium,
            isError = isErrorEmail
        )
        if (isErrorEmail) ErrorMessage(text = "Email é obrigatório!")
        CustomInput(
            textInput = username,
            onValueChange = { letter ->
                username = letter
            },
            label = "Insert your username:",
            placeholder = "Insira seu username:",
            icon = Icons.Filled.AccountCircle,
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
        CustomInput(
            textInput = checkPassword,
            onValueChange = { letter ->
                checkPassword = letter
            },
            label = "Insert your password:",
            placeholder = "Insira sua senha:",
            icon = Icons.Filled.Lock,
            keyboard = KeyboardType.Password,
            isPassword = true,
            color = OceanBlue,
            cornerShape = ShapeButton.medium,
            isError = isErrorCheckPassword
        )
        if (isErrorCheckPassword) ErrorMessage(text = "Senha é obrigatório!")
        
//        errorMessage?.let { ErrorMessage(text = it) }
        CustomButton(
            onClick = { navController?.navigate("login") },
            colorsList = buttonColors,
            text = "Register",
            cornerShape = ShapeButton.medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterScreenPrev() {
    RegisterScreen()
}