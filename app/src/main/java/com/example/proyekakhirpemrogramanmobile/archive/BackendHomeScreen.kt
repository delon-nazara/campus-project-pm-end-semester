package com.example.proyekakhirpemrogramanmobile.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyekakhirpemrogramanmobile.R

@Preview(showSystemUi = true)
@Composable
fun BackendHomeScreenPreview() {
    BackendHomeScreen(
        imageUrl = "",
        email = "",
        onLogoutButtonClicked = {}
    )
}

@Composable
fun BackendHomeScreen(
    imageUrl: String,
    email: String?,
    onLogoutButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 100.dp)
    ) {
        Text(
            text = stringResource(R.string.this_is_home_screen),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(150.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.display_email, email ?: "not found"),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
        Button(
            onClick = onLogoutButtonClicked,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = stringResource(R.string.logout))
        }
    }
}