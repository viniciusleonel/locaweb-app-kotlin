package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.unit.dp
import br.dev.locaweb_app.ui.theme.OceanBlue
import br.dev.locaweb_app.ui.theme.RegularShape

@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    textInput: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle? = null,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    color: Color? = null,
    cornerShape: CornerBasedShape? = null,
    icon: ImageVector? = null,
    iconDescription: String? = "",
    label: String? = null,
    placeholder: String? = null,
    keyboard: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    isPassword: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    themeViewModel: ThemeViewModel? = null
) {

    val isDarkTheme = themeViewModel?.isDarkTheme?.value
    val usersColor = themeViewModel?.navBarColor?.value ?: OceanBlue


    var text by remember {
        mutableStateOf(textInput)
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 10.dp),
        value = text,
        onValueChange = { letter ->
            text = letter
            onValueChange(letter)
        },
        label = {
            label?.let {
                Text(text = it)
            }
        },
        placeholder = {
            placeholder?.let {
                Text(
                    text = it,
                )
            }
        },

        leadingIcon = if (icon != null) {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription
                )
            }
        } else null,

        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboard,
            capitalization = capitalization
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = textStyle ?: TextStyle(),
        colors = TextFieldDefaults.colors(
            focusedLabelColor = color ?: usersColor,
            focusedTextColor = if (isDarkTheme == true) Color.White else Color.Black,
            focusedIndicatorColor = color ?: usersColor,
            focusedLeadingIconColor = color ?: usersColor,
            focusedPlaceholderColor = Color.Gray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color(0xFFB7BDE7),
            disabledTextColor = Color.Gray,
            disabledLeadingIconColor = Color.Gray
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        shape = cornerShape ?: RegularShape,
        isError = isError,
        enabled = enabled
    )
}