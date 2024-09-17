package br.dev.locaweb_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.UserLogin
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.user.login
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
    userViewModel: UserViewModel,
    themeViewModel: ThemeViewModel,
    buttonColors: List<Color>? = null
) {

    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    var loginResponse by remember { mutableStateOf(UserLoginResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isErrorUsername by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }

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
            themeViewModel = themeViewModel,
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
            themeViewModel = themeViewModel,
            isError = isErrorPassword
        )
        if (isErrorPassword) ErrorMessage(text = "Senha é obrigatório!")
        Spacer(modifier = Modifier.height(10.dp))
        CustomButton(
            onClick = {

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val userLogin = UserLogin(username.lowercase().replace(" ", ""), password)
                    navController?.let {
                        userLogin.login(
                            onSuccess = { response ->
                                loginResponse = response
                                userViewModel.setUserLoginResponse(response)

                                snackBarViewModel.showSuccessSnackbar()
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Login realizado com sucesso!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                navController.navigate("my-emails")
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
                            },
                            navController = navController
                        )
                    }
                } else {
                    isErrorUsername = username.isEmpty()
                    isErrorPassword = password.isEmpty()
                }

            },
            text = "Login",
            colorsList = buttonColors
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            onClick = { navController?.navigate("register") },
            text = "Register",
            colorsList = buttonColors
        )
    }
}


