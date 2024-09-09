package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.dev.locaweb_app.ui.theme.ShapeButton

@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    textStyle: TextStyle? = null,
    color: Color? = null,
    cornerShape: CornerBasedShape? = null,
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
    OutlinedTextField(
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
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = textStyle ?: TextStyle(Color.Black),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = color ?: Color.Black,
            focusedIndicatorColor = color ?: Color.Black,
            focusedLeadingIconColor = color ?: Color.Black,
            focusedPlaceholderColor = Color.Gray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        singleLine = true,
        shape = cornerShape ?: ShapeButton.small
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomInputPrev() {
    Column {
        CustomInput(
            textStyle = TextStyle(Color.Blue),
            label = "Insert your username:",
            icon = Icons.Filled.Person,
            capitalization = KeyboardCapitalization.Words,
        )
        CustomInput(
            textStyle = TextStyle(Color.Blue),
            label = "Email",
            keyboard = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None,
        )
        CustomInput(
            textStyle = TextStyle(Color.Blue),
            label = "Numbers",
            keyboard = KeyboardType.Number,
        )
        CustomInput(
            textStyle = TextStyle(Color.Blue),
            label = "Insert your password:",
            keyboard = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
        )
    }
}
