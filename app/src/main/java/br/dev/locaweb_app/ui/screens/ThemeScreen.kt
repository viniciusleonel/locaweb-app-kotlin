package br.dev.locaweb_app.ui.screens

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
import br.dev.locaweb_app.model.preferences.UserPreferences
import br.dev.locaweb_app.model.preferences.UserPreferencesResponse
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.preferences.updatePreferences
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.ShowColorPicker
import br.dev.locaweb_app.ui.components.SnackBarViewModel
import br.dev.locaweb_app.ui.components.ThemeViewModel
import br.dev.locaweb_app.ui.theme.darkenColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ThemeScreen(
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
    var theme by remember { mutableStateOf(if (isDarkTheme) "Dark" else "Light") }
    var colorScheme by remember { mutableStateOf(usersColor.toString()) }
    var categories by remember { mutableStateOf("") }
    var labels by remember { mutableStateOf("") }
    var userPreferencesResponse by remember { mutableStateOf(UserPreferencesResponse()) }
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
        verticalArrangement = Arrangement.SpaceBetween, // Garante que o botão fique no final
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
            Spacer(modifier = Modifier.padding(0.dp, 5.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ShowColorPicker(themeViewModel, buttonColors)
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = {
                val userPreferences =
                    userPreferencesId?.let { setUpPreferences(if (isDarkTheme) "Dark" else "Light", usersColor.toString(), categories, labels, it) }

                if (userPreferences?.theme != null || userPreferences?.colorScheme != null || userPreferences?.categories != null || userPreferences?.labels != null) {
                    userPreferences.updatePreferences(userPreferencesId,
                        onSuccess = { response ->
                            userPreferencesResponse = response
                            snackBarViewModel.showSuccessSnackbar()
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Preferências salvas!",
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
    }
}
