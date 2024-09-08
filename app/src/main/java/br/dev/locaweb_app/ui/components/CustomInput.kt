package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconDescription: String? = "",
    label: String? = null,
    placeholder: String? = null,
    keyboard: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    isPassword: Boolean = false
) {

    var text by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp),
        value = text,
        onValueChange = {
            letter -> text = letter
        },
        label = {
            label?.let {
                Text(text = it)
            }
        },
        placeholder = {
            placeholder?.let {
                Text(text = it)
            }
        },
        leadingIcon = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = iconDescription)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboard,
            capitalization = capitalization
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomInputPrev() {
    Column {
        CustomInput(
            label = "Insert your username:",
            icon = Icons.Filled.Person,
            capitalization = KeyboardCapitalization.Words
        )
        CustomInput(
            label = "Email",
            keyboard = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None
        )
        CustomInput(
            label = "Numbers",
            keyboard = KeyboardType.Number
        )
        CustomInput(
            label = "Insert your password:",
            keyboard = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None
        )
    }
}
