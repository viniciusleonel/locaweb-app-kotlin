package br.dev.locaweb_app.ui.screens.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.EmailsList
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.email.listReceivedEmails
import br.dev.locaweb_app.service.email.listSentEmails
import br.dev.locaweb_app.ui.components.EmailListDisplay
import br.dev.locaweb_app.ui.components.EmailListType
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun EmailListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null,
    themeViewModel: ThemeViewModel,
    userViewModel: UserViewModel,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
    emailListType: EmailListType
) {
    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value
    val user = userViewModel.userLoginResponse.value
    val coroutineScope = rememberCoroutineScope()
    var searchError by remember { mutableStateOf(false) }
    var listEmailsResponse by remember { mutableStateOf<EmailsList?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    DisposableEffect(Unit) {
        coroutineScope.launch {
            if (user != null) {
                when (emailListType) {
                    EmailListType.INBOX -> {
                        listReceivedEmails(
                            user.id,
                            onSuccess = { response ->
                                listEmailsResponse = response
                                snackBarViewModel.showSuccessSnackbar()
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Emails loaded successfully!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                isLoading = false
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
                                isLoading = false
                            },
                            navController = navController
                        )
                    }

                    EmailListType.OUTBOX -> {
                        listSentEmails(
                            user.id,
                            onSuccess = { response ->
                                listEmailsResponse = response
                                snackBarViewModel.showSuccessSnackbar()
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Emails loaded successfully!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                isLoading = false
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
                                isLoading = false
                            },
                            navController = navController
                        )
                    }
                }
            } else {
                searchError = true
                isLoading = false
            }
        }
        onDispose {}
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (isLoading) {
            Text("Loading...")
        } else if (errorMessage != null) {
            Text("Error: $errorMessage")
        } else {
            listEmailsResponse?.let { emailsList ->
                EmailListDisplay(
                    emailsList = emailsList,
                    color = usersColor,
                    emailListType = emailListType,
                    navController = navController
                )
            } ?: run {
                Text("No emails found.")
            }
        }
    }
}
