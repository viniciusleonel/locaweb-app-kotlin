package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.UserRegister
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.service.user.register
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
    themeViewModel: ThemeViewModel,
    buttonColors: List<Color>? = null
) {

    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    LaunchedEffect(usersColor) {
        systemUiController.setStatusBarColor(color = usersColor)
    }

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
    var passwordsMatchError by remember { mutableStateOf(false) }
    var userRegisterResponse by remember { mutableStateOf(UserRegisterResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun clearErrors() {
        isErrorName = false
        isErrorEmail = false
        isErrorUsername = false
        isErrorPassword = false
        isErrorCheckPassword = false
        passwordsMatchError = false
    }

    fun formIsEmpty(): Boolean {
        clearErrors()
        isErrorName = name.isEmpty()
        isErrorEmail = email.isEmpty()
        isErrorUsername = username.isEmpty()
        isErrorPassword = password.isEmpty()
        isErrorCheckPassword = checkPassword.isEmpty()
        if (isErrorName || isErrorEmail || isErrorUsername || isErrorPassword || isErrorCheckPassword) {
            return true
        }
        if (password != checkPassword) {
            passwordsMatchError = true
            return true
        }
        return false
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomInput(
            textInput = name,
            onValueChange = { name = it },
            label = "Insert your name:",
            placeholder = "Insira seu nome:",
            icon = Icons.Filled.Person,
            themeViewModel = themeViewModel,
            capitalization = KeyboardCapitalization.Words,
            isError = isErrorName
        )
        if (isErrorName) ErrorMessage(text = "Nome é obrigatório!")

        CustomInput(
            textInput = email,
            onValueChange = { email = it },
            label = "Insert your email:",
            placeholder = "Insira seu email:",
            icon = Icons.Filled.Email,
            themeViewModel = themeViewModel,
            keyboard = KeyboardType.Email,
            isError = isErrorEmail
        )
        if (isErrorEmail) ErrorMessage(text = "Email é obrigatório!")

        CustomInput(
            textInput = username,
            onValueChange = { username = it },
            label = "Insert your username:",
            placeholder = "Insira seu username:",
            icon = Icons.Filled.AccountCircle,
            themeViewModel = themeViewModel,
            isError = isErrorUsername
        )
        if (isErrorUsername) ErrorMessage(text = "Username é obrigatório!")

        CustomInput(
            textInput = password,
            onValueChange = { password = it },
            label = "Insert your password:",
            placeholder = "Insira sua senha:",
            icon = Icons.Filled.Lock,
            keyboard = KeyboardType.Password,
            isPassword = true,
            themeViewModel = themeViewModel,
            isError = isErrorPassword || passwordsMatchError
        )
        if (isErrorPassword) ErrorMessage(text = "Senha é obrigatório!")

        CustomInput(
            textInput = checkPassword,
            onValueChange = { checkPassword = it },
            label = "Confirm your password:",
            placeholder = "Confirme sua senha:",
            icon = Icons.Filled.Lock,
            keyboard = KeyboardType.Password,
            isPassword = true,
            themeViewModel = themeViewModel,
            isError = isErrorCheckPassword || passwordsMatchError
        )
        if (isErrorCheckPassword) ErrorMessage(text = "Confirmação de senha é obrigatória!")
        if (passwordsMatchError) ErrorMessage(text = "As senhas precisam ser iguais!")
        val userRegister = UserRegister(name, email, username, password)
        CustomButton(
            onClick = {

                if (!formIsEmpty()) {
                    navController?.let {
                        userRegister.register(onSuccess = { response ->
                            userRegisterResponse = response
                            snackBarViewModel.showSuccessSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Registrado! Realize o login.",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            navController.navigate("login")
                        },
                            onFailure = { message ->
                                errorMessage = message
                                snackBarViewModel.showErrorSnackbar()
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = errorMessage.toString(),
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                if (errorMessage.equals("Login expired!"))
                                    navController.navigate("login")
                            }
                        )
                    }
                } else {
                    snackBarViewModel.showErrorSnackbar()
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Erro ao registrar!",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            },
            text = "Register",
            colorsList = buttonColors
        )
    }
}
