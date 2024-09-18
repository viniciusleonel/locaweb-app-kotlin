package br.dev.locaweb_app.ui.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.user.UserLoginResponse
import br.dev.locaweb_app.model.user.UserUpdate
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.user.deleteUserById
import br.dev.locaweb_app.service.user.update
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.DeleteDialog
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.theme.ButtonColorsWarning
import br.dev.locaweb_app.ui.theme.Typography
import br.dev.locaweb_app.utils.reloadScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
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

    val user = userViewModel.userLoginResponse.value
    var name by remember { mutableStateOf(user?.name ?: "") }
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorCheckPassword by remember { mutableStateOf(false) }
    var passwordsMatchError by remember { mutableStateOf(false) }

    var userUpdateResponse by remember { mutableStateOf(UserLoginResponse()) }
    var userDeleteResponse by remember { mutableStateOf(MessageResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var isEditing by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    fun clearErrors() {
        isErrorPassword = false
        isErrorCheckPassword = false
        passwordsMatchError = false
    }

    fun createUserUpdate(
        name: String,
        email: String,
        username: String,
        password: String
    ): UserUpdate {
        return UserUpdate(
            name = name.ifEmpty { null },
            email = email.ifEmpty { null },
            username = username.ifEmpty { null },
            password = password.ifEmpty { null }
        )
    }

    fun verifyPassword(password: String, confirmPassword: String): Boolean {
        return if (password.isNotEmpty()) {
            if (confirmPassword.isEmpty()) false
            else password == confirmPassword
        } else true
    }

    fun formIsValid(password: String, confirmPassword: String): Boolean {
        clearErrors()
        if (!verifyPassword(password, confirmPassword)) {
            passwordsMatchError = true
            return false
        }
        return true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user != null) {
            Column() {
                Text(
                    text = "Name:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = name,
                    onValueChange = { name = it },
                    icon = Icons.Filled.Person,
                    capitalization = KeyboardCapitalization.Words,
                    enabled = isEditing,
                    themeViewModel = themeViewModel
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Email:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = email,
                    onValueChange = { email = it },
                    icon = Icons.Filled.Email,
                    keyboard = KeyboardType.Email,
                    enabled = isEditing,
                    themeViewModel = themeViewModel
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Username:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = username,
                    onValueChange = { username = it },
                    icon = Icons.Filled.AccountCircle,
                    enabled = isEditing,
                    themeViewModel = themeViewModel
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Password:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = password,
                    onValueChange = { password = it },
                    icon = Icons.Filled.Lock,
                    keyboard = KeyboardType.Password,
                    isPassword = true,
                    isError = isErrorPassword || passwordsMatchError,
                    enabled = isEditing,
                    themeViewModel = themeViewModel
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Confirm Password:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = checkPassword,
                    onValueChange = { checkPassword = it },
                    icon = Icons.Filled.Lock,
                    keyboard = KeyboardType.Password,
                    isPassword = true,
                    isError = isErrorCheckPassword || passwordsMatchError,
                    enabled = isEditing,
                    themeViewModel = themeViewModel
                )
                if (isErrorCheckPassword) ErrorMessage(text = "Password confirmation is required!")
                if (passwordsMatchError) ErrorMessage(text = "Passwords do not match!")

                Spacer(modifier = Modifier.height(5.dp))

                if (isEditing) {
                    CustomButton(
                        onClick = {

                            if (formIsValid(password, checkPassword)) {
                                val userUpdate = createUserUpdate(name, email, username, password)

                                if (userUpdate.name != null || userUpdate.email != null ||
                                    userUpdate.username != null || userUpdate.password != null
                                ) {


                                    userUpdate.update(
                                        user.id,
                                        onSuccess = { response ->
                                            userUpdateResponse = response
                                            snackBarViewModel.showSuccessSnackbar()
                                            scope.launch {
                                                snackBarHostState.showSnackbar(
                                                    message = "Updated successfully!",
                                                    duration = SnackbarDuration.Short
                                                )
                                            }

                                            isEditing = false
                                        },
                                        onFailure = { message ->
                                            errorMessage = message
                                            snackBarViewModel.showErrorSnackbar()
                                            scope.launch {
                                                snackBarHostState.showSnackbar(
                                                    message = errorMessage.toString(),
                                                    duration = SnackbarDuration.Long
                                                )
                                            }
                                        },
                                        navController = navController
                                    )
                                } else {
                                    snackBarViewModel.showRegularSnackbar()
                                    scope.launch {
                                        snackBarHostState.showSnackbar(
                                            message = "No changes detected!",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            }

                        },
                        text = "Save Changes",
                        colorsList = buttonColors
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomButton(
                        onClick = {
                            snackBarViewModel.showRegularSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "No changes detected!",
                                    duration = SnackbarDuration.Short
                                )
                            }
                            isEditing = false
                            reloadScreen(navController, "edit-profile")
                        },
                        colorsList = ButtonColorsWarning,
                        text = "Cancel Changes"
                    )
                } else {
                    CustomButton(
                        onClick = {
                            isEditing = true
                        },
                        text = "Edit Profile",
                        colorsList = buttonColors
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomButton(
                        onClick = {
                            showDialog = true
                        },
                        colorsList = ButtonColorsWarning,
                        text = "Delete Account"
                    )
                }

                if (showDialog)
                    DeleteDialog(
                        buttonColors = buttonColors,
                        dialogState = showDialog,
                        onConfirmRequest = {

                            deleteUserById(
                                user.id,
                                onSuccess = { response ->
                                    userDeleteResponse = response
                                    snackBarViewModel.showSuccessSnackbar()
                                    scope.launch {
                                        snackBarHostState.showSnackbar(
                                            message = "User deleted successfully!",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                    showDialog = false
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
                                    showDialog = false
                                },
                                navController = navController
                            )
                        },
                        onDismissRequest = { showDialog = false }
                    )
            }
        } else {
            Text(text = "No user data available")
        }
    }
}
