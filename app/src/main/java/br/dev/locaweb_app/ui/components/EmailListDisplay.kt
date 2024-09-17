package br.dev.locaweb_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.dev.locaweb_app.model.email.EmailData
import br.dev.locaweb_app.model.email.EmailsList

@Composable
fun EmailListDisplay(
    emailsList: EmailsList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(emailsList.content) { email ->
            EmailListItem(email)
        }
    }
}

@Composable
fun EmailListItem(email: EmailData) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* Handle item click */ },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = email.subject,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = email.sendByUser,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = email.sendAt, // Format date as needed
        )
    }
}
