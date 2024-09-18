package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.dev.locaweb_app.model.email.EmailData
import br.dev.locaweb_app.model.email.EmailsList
import br.dev.locaweb_app.model.user.UserViewModel
import br.dev.locaweb_app.service.email.getEmailByIdAndUserId
import br.dev.locaweb_app.ui.screens.EmailViewScreen
import br.dev.locaweb_app.utils.formatDate
import kotlinx.coroutines.CoroutineScope

enum class EmailListType {
    INBOX,
    OUTBOX
}

@Composable
fun EmailListDisplay(
    modifier: Modifier = Modifier,
    emailsList: EmailsList,
    color: Color,
    emailListType: EmailListType,
    userViewModel: UserViewModel,
    themeViewModel: ThemeViewModel,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
) {
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        when (emailListType) {
            EmailListType.OUTBOX -> {
                Text(
                    text = "Enviados",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                )
            }

            EmailListType.INBOX -> {
                Text(
                    text = "Recebidos",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                )
            }
        }
        Text(
            text = "Assunto",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 26.sp,
        )

    }
    Spacer(modifier = Modifier.height(10.dp))
    HorizontalDivider(
        thickness = 1.dp,
        color = color.copy(alpha = 0.4f)
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(emailsList.content) { email ->
            EmailListItem(
                email,
                color,
                emailListType,
                userViewModel,
                themeViewModel,
                navController,
                snackBarHostState,
                snackBarViewModel,
                scope
            )
        }
    }
}

@Composable
fun EmailListItem(
    email: EmailData,
    color: Color,
    emailListType: EmailListType,
    userViewModel: UserViewModel,
    themeViewModel: ThemeViewModel,
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    snackBarViewModel: SnackBarViewModel,
    scope: CoroutineScope,
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("emailView/${email.id}")

            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                when (emailListType) {
                    EmailListType.INBOX -> {
                        Row {
                            Text(
                                text = email.sendByUser,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                            )
                        }
                    }

                    EmailListType.OUTBOX -> {
                        Row {
                            Text(
                                text = email.receiveByUser,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.width(175.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = email.subject,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatDate(email.sendAt),
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = color.copy(alpha = 0.6f)
            )
        }
    }
}
