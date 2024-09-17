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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.locaweb_app.model.email.EmailData
import br.dev.locaweb_app.model.email.EmailsList

@Composable
fun EmailListDisplay(
    modifier: Modifier = Modifier,
    emailsList: EmailsList,
    color: Color
) {
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Enviado para:",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 26.sp,
        )
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
            EmailListItem(email, color)

        }
    }
}

@Composable
fun EmailListItem(
    email: EmailData,
    color: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click */ },
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
                Row {
                    Text(
                        text = email.receiveByUser,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                    )
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
                text = email.sendAt, // Format date as needed
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = color.copy(alpha = 0.6f)
            )
        }
    }
}
