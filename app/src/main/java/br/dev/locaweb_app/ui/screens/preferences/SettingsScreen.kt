package br.dev.locaweb_app.ui.screens.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserLogout
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.user.logout
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null,
    themeViewModel: ThemeViewModel,
    userViewModel: UserViewModel,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
) {

    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value
    val user = userViewModel.userLoginResponse.value
    var logoutResponse by remember { mutableStateOf(MessageResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    Column(
        modifier = modifier
    ) {
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
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            onClick = {
                user?.username?.let { UserLogout(it) }?.let {
                    logout(
                        it,
                        onSuccess = { response ->
                            logoutResponse = response
                            snackBarViewModel.showSuccessSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Logout successfully!",
                                    duration = SnackbarDuration.Short
                                )
                            }
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
                        navController
                    )
                }
                navController.navigate("login")
            },
            text = "Logout",
            colorsList = buttonColors
        )
    }
}
