package br.dev.locaweb_app.ui.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.MessageResponse
import br.dev.locaweb_app.model.preferences.UserPreferences
import br.dev.locaweb_app.model.preferences.UserPreferencesResponse
import br.dev.locaweb_app.model.user.UserLogout
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.preferences.updatePreferences
import br.dev.locaweb_app.service.user.logout
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ShowColorPicker
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.theme.darkenColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel,
    themeViewModel: ThemeViewModel,
    navController: NavController,
    buttonColors: List<Color>? = null,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
) {
    val usersColor = themeViewModel.navBarColor.value

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = usersColor)
    }

    val isDarkTheme = themeViewModel.isDarkTheme.value
    val darkNavBarColor = darkenColor(usersColor, 0.4f)

    val user = userViewModel.userLoginResponse.value
    val userPreferencesId = user?.userPreferences?.id
    val categories by remember { mutableStateOf("") }
    val labels by remember { mutableStateOf("") }
    var userPreferencesResponse by remember { mutableStateOf(UserPreferencesResponse()) }
    var logoutResponse by remember { mutableStateOf(MessageResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun setUpPreferences(
        theme: String,
        colorScheme: String,
        categories: String,
        labels: String,
        userId: Long
    ): UserPreferences {
        return UserPreferences(
            theme = theme.ifEmpty { null },
            colorScheme = colorScheme.ifEmpty { null },
            categories = categories.ifEmpty { null },
            labels = labels.ifEmpty { null },
            userId = userId
        )
    }

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = "Choose your theme:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Current theme: " + if (isDarkTheme) "Dark" else "Light",
                    fontSize = 18.sp,
                )

                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { themeViewModel.toggleTheme() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = usersColor,
                        checkedTrackColor = darkNavBarColor,
                        uncheckedTrackColor = Color.LightGray,
                        uncheckedThumbColor = darkNavBarColor,
                        uncheckedBorderColor = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = usersColor.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Choose favorite color:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ShowColorPicker(themeViewModel, buttonColors)
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = usersColor.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Edit your profile:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                onClick = {
                    navController.navigate("edit-profile")
                },
                text = "Edit Profile",
                colorsList = buttonColors
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = usersColor.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Save your preferences:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    val userPreferences =
                        userPreferencesId?.let {
                            setUpPreferences(
                                if (isDarkTheme) "Dark" else "Light",
                                usersColor.toString(),
                                categories,
                                labels,
                                it
                            )
                        }

                    if (userPreferences?.theme != null || userPreferences?.colorScheme != null || userPreferences?.categories != null || userPreferences?.labels != null) {
                        userPreferences.updatePreferences(
                            userPreferencesId,
                            onSuccess = { response ->
                                userPreferencesResponse = response
                                snackBarViewModel.showSuccessSnackbar()
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Preferences saved!",
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
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            },
                            navController = navController
                        )
                    }
                },
                text = "Save Preferences",
                colorsList = buttonColors
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = usersColor.copy(alpha = 0.6f)
            )
        }

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
