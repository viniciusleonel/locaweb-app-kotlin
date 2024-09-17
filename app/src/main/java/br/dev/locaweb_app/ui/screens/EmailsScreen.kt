package br.dev.locaweb_app.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.Email
import br.dev.locaweb_app.model.email.SentEmail
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.email.sendEmail
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun EmailsScreen(
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
    var emailResponse by remember { mutableStateOf(SentEmail()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val user = userViewModel.userLoginResponse.value
    val email = Email("", "" ,"" ,"")

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    Column {
        CustomButton(
            onClick = {
                navController.navigate("my-emails")
            },
            colorsList = buttonColors,
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "Emails"
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            onClick = {
                navController.navigate("send-email")
            },
            colorsList = buttonColors,
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "Send Email"
        )
    }
}
