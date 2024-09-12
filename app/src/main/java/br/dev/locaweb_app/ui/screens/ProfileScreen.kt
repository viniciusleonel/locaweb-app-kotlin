package br.dev.locaweb_app.ui.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.dev.locaweb_app.model.user.UserRegisterResponse
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.ui.components.CustomButton
import br.dev.locaweb_app.ui.components.CustomInput
import br.dev.locaweb_app.ui.components.ErrorMessage
import br.dev.locaweb_app.ui.theme.ButtonColors
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.ShapeButton
import br.dev.locaweb_app.ui.theme.Typography

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    userViewModel: UserViewModel,
) {
    val user = userViewModel.userLoginResponse.value
    var name by remember { mutableStateOf(user?.name ?: "") }
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }
    var isErrorName by remember { mutableStateOf(false) }
    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorUsername by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorCheckPassword by remember { mutableStateOf(false) }
    var passwordsMatchError by remember { mutableStateOf(false) }
    var userRegisterResponse by remember { mutableStateOf(UserRegisterResponse()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isEditing by remember { mutableStateOf(false) }

    fun clearErrors() {
        isErrorName = false
        isErrorEmail = false
        isErrorUsername = false
        isErrorPassword = false
        isErrorCheckPassword = false
        passwordsMatchError = false
    }

    fun formIsEmpty(): Boolean {
        clearErrors()
        isErrorName = name.isEmpty()
        isErrorEmail = email.isEmpty()
        isErrorUsername = username.isEmpty()
        isErrorPassword = password.isEmpty()
        isErrorCheckPassword = checkPassword.isEmpty()
        if (isErrorName || isErrorEmail || isErrorUsername || isErrorPassword || isErrorCheckPassword) {
            return true
        }
        if (password != checkPassword) {
            passwordsMatchError = true
            return true
        }
        return false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user != null) {
            Column (){
                Text(
                    text = "Name:",
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                CustomInput(
                    textInput = name,
                    onValueChange = { name = it },
                    icon = Icons.Filled.Person,
                    color = OceanBlue,
                    capitalization = KeyboardCapitalization.Words,
                    cornerShape = ShapeButton.medium,
                    isError = isErrorName,
                    enabled = isEditing // Only editable if `isEditing` is true
                )
                if (isErrorName) ErrorMessage(text = "Name é obrigatório!")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Email:", style = Typography.labelMedium, modifier = Modifier.padding(20.dp, 0.dp))
                CustomInput(
                    textInput = email,
                    onValueChange = { email = it },
                    icon = Icons.Filled.Email,
                    color = OceanBlue,
                    keyboard = KeyboardType.Email,
                    cornerShape = ShapeButton.medium,
                    isError = isErrorEmail,
                    enabled = isEditing
                )
                if (isErrorEmail) ErrorMessage(text = "Email é obrigatório!")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Username:", style = Typography.labelMedium, modifier = Modifier.padding(20.dp, 0.dp))
                CustomInput(
                    textInput = username,
                    onValueChange = { username = it },
                    icon = Icons.Filled.AccountCircle,
                    color = OceanBlue,
                    cornerShape = ShapeButton.medium,
                    isError = isErrorUsername,
                    enabled = isEditing
                )
                if (isErrorUsername) ErrorMessage(text = "Username é obrigatório!")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Password:", style = Typography.labelMedium, modifier = Modifier.padding(20.dp, 0.dp))
                CustomInput(
                    textInput = password,
                    onValueChange = { password = it },
                    icon = Icons.Filled.Lock,
                    keyboard = KeyboardType.Password,
                    color = OceanBlue,
                    cornerShape = ShapeButton.medium,
                    isError = isErrorPassword || passwordsMatchError,
                    enabled = isEditing
                )
                if (isErrorPassword) ErrorMessage(text = "Senha é obrigatório!")
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = "Confirm Password:", style = Typography.labelMedium, modifier = Modifier.padding(20.dp, 0.dp))
                CustomInput(
                    textInput = checkPassword,
                    onValueChange = { checkPassword = it },
                    icon = Icons.Filled.Lock,
                    keyboard = KeyboardType.Password,
                    color = OceanBlue,
                    cornerShape = ShapeButton.medium,
                    isError = isErrorCheckPassword || passwordsMatchError,
                    enabled = isEditing
                )
                if (isErrorCheckPassword) ErrorMessage(text = "Confirmação de senha é obrigatória!")
                if (passwordsMatchError) ErrorMessage(text = "As senhas precisam ser iguais!")

                Spacer(modifier = Modifier.height(5.dp))

                // Edit and Save button logic
                if (isEditing) {
                    CustomButton(
                        onClick = {
                            if (!formIsEmpty()) {
                                // Save changes to the ViewModel or backend API
                                userViewModel.updateUserProfile(name, username, email)
                                isEditing = false // End editing mode
                            }
                        },
                        cornerShape = ShapeButton.medium,
                        colorsList = ButtonColors,
                        text = "Save Changes"
                    )
                } else {
                    CustomButton(
                        onClick = {
                            isEditing = true // Switch to editing mode
                        },
                        cornerShape = ShapeButton.medium,
                        colorsList = ButtonColors,
                        text = "Edit Profile"
                    )
                }
            }
        } else {
            Text(text = "No user data available")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPrev() {
    val userViewModel: UserViewModel = viewModel()
    ProfileScreen(userViewModel = userViewModel)
}
