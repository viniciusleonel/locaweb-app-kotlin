package br.dev.locaweb_app.ui.screens.email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.Email
import br.dev.locaweb_app.model.email.SentEmail
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.email.sendEmail
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.utils.reloadScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun SendEmailScreen(
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

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    val user = userViewModel.userLoginResponse.value
    val sender by remember { mutableStateOf(user?.email ?: "") }
    var recipient by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isErrorRecipient by remember { mutableStateOf(false) }
    var isErrorSubject by remember { mutableStateOf(false) }

    fun clearErrors() {
        isErrorRecipient = false
        isErrorSubject = false
    }

    fun formIsEmpty(): Boolean {
        clearErrors()
        isErrorRecipient = recipient.isEmpty()
        isErrorSubject = subject.isEmpty()
        return isErrorRecipient || isErrorSubject
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomInput(
            textInput = recipient,
            onValueChange = { recipient = it },
            label = "Recipient:",
            placeholder = "",
            themeViewModel = themeViewModel,
            keyboard = KeyboardType.Email,
            isError = isErrorRecipient
        )
        if (isErrorRecipient) ErrorMessage(text = "Recipient is required!")

        CustomInput(
            textInput = subject,
            onValueChange = { subject = it },
            label = "Subject:",
            placeholder = "",
            themeViewModel = themeViewModel,
            capitalization = KeyboardCapitalization.Words,
            isError = isErrorSubject
        )
        if (isErrorSubject) ErrorMessage(text = "Subject is required!")

        CustomInput(
            modifier = Modifier
                .height(200.dp),
            textInput = message,
            onValueChange = { message = it },
            label = "Message:",
            placeholder = "",
            themeViewModel = themeViewModel,
            capitalization = KeyboardCapitalization.Words,
            singleLine = false,
            maxLines = 30
        )
        CustomButton(
            onClick = {
                if (!formIsEmpty()) {
                    val email = Email(sender, recipient, subject, message)
                    email.sendEmail(
                        onSuccess = { response ->
                            emailResponse = response
                            snackBarViewModel.showSuccessSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Email sent successfully!",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            reloadScreen(navController, "send-email")
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
            },
            colorsList = buttonColors,
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "Send Email"
        )
    }
}
