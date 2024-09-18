package br.dev.locaweb_app.ui.screens.email

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.email.EmailDetails
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.email.deleteEmailById
import br.dev.locaweb_app.service.email.getEmailByIdAndUserId
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.utils.formatDate
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EmailViewScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    buttonColors: List<Color>? = null,
    themeViewModel: ThemeViewModel,
    userViewModel: UserViewModel,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
    emailId: Long
) {
    val systemUiController = rememberSystemUiController()
    val usersColor = themeViewModel.navBarColor.value
    val user = userViewModel.userLoginResponse.value
    val coroutineScope = rememberCoroutineScope()
    var emailResponse by remember { mutableStateOf<EmailDetails?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    DisposableEffect(Unit) {
        coroutineScope.launch {
            if (user != null) {
                getEmailByIdAndUserId(
                    emailId, user.id,
                    onSuccess = { response ->
                        emailResponse = response
                        snackBarViewModel.showSuccessSnackbar()
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = "Email loaded successfully!",
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
                    navController
                )
            }
        }
        onDispose {}
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                Text(text = "Loading...", color = Color.Gray)
            }

            errorMessage != null -> {
                Text(text = "Error: $errorMessage", color = Color.Red)
            }

            emailResponse != null -> {
                EmailDetailsView(
                    emailDetails = emailResponse,
                    emailId,
                    navController,
                    snackBarHostState,
                    snackBarViewModel,
                    scope,
                    usersColor
                )
            }
        }
    }
}

@Composable
fun EmailDetailsView(
    emailDetails: EmailDetails?,
    emailId: Long,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
    color: Color
) {

    var deleteResponse by remember { mutableStateOf(MessageResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            if (emailDetails != null) {
                Text(
                    text = emailDetails.subject,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier.clickable(onClick = {
                    deleteEmailById(emailId,
                        onSuccess = { response ->
                            deleteResponse = response
                            snackBarViewModel.showSuccessSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Email deleted!",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            navController.popBackStack()
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
                })
            )
            
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (emailDetails != null) {
            Text(
                text = "From: ${emailDetails.sendByUser}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        if (emailDetails != null) {
            Text(
                text = "To: ${emailDetails.receiveByUser}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (emailDetails != null) {
            Text(
                text = "Sent at: ${formatDate(emailDetails.sendAt)}",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (emailDetails != null) {
            Text(
                text = emailDetails.body,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}