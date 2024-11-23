package com.example.proyekakhirpemrogramanmobile.archive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R

@Composable
fun BaseScreen(
    onRegisterScreenButton: () -> Unit,
    onLoginScreenButton: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 100.dp)
    ) {
        Text(
            text = stringResource(R.string.this_is_base_screen),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Button(
                onClick = onRegisterScreenButton,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.go_to_register_screen))
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = onLoginScreenButton,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.go_to_login_screen))
            }
        }
    }
}