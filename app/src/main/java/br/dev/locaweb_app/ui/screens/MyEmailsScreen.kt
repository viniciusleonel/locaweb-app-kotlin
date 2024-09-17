package br.dev.locaweb_app.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import br.dev.locaweb_app.service.email.listEmails
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.EmailListDisplay
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MyEmailsScreen(
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
    val coroutineScope = rememberCoroutineScope()
    var search by remember { mutableStateOf("") }
    var searchError by remember { mutableStateOf(false) }
    var listEmailsResponse by remember { mutableStateOf<EmailsList?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    DisposableEffect(Unit) {
        coroutineScope.launch {
            // Chame sua função de API aqui
            if (user != null) {
                listEmails(
                    user.id,
                    onSuccess = { response ->
                        listEmailsResponse = response
                        snackBarViewModel.showSuccessSnackbar()
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Emails carregados com sucesso!",
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
                        Log.i("Erro lista emails", errorMessage!!)
                        isLoading = false
                    },
                    navController = navController
                )
            } else {
                searchError = true
                isLoading = false
            }
        }
        onDispose {
            // Cleanup se necessário
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Adjust padding as needed
    ) {
        CustomInput(
            textInput = search,
            onValueChange = { search = it },
            label = "Search:",
            placeholder = "Search",
            icon = Icons.Filled.Search,
            themeViewModel = themeViewModel,
            isError = searchError
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            Text("Loading...")
        } else if (errorMessage != null) {
            Text("Error: $errorMessage")
        } else {
            listEmailsResponse?.let { emailsList ->
                EmailListDisplay(emailsList = emailsList)
            } ?: run {
                Text("No emails found.")
            }
        }
    }
}
