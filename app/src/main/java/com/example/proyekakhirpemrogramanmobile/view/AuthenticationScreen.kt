package com.example.proyekakhirpemrogramanmobile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.utils.Poppins

@Preview(showBackground = true)
@Composable
fun AuthenticationScreenPreview() {
    val showLoginScreenPreview = false
    if (showLoginScreenPreview) {
        // Login screen preview
        AuthenticationScreen(
            screenTitle = stringResource(R.string.authentication_login_title),
            mainText = stringResource(R.string.authentication_login),
            sideText = stringResource(R.string.authentication_register),
            navigationText = stringResource(R.string.authentication_not_have_account),
            onTopButtonClicked = {},
            onBottomButtonClicked = {}
        )
    } else {
        // Register screen preview
        AuthenticationScreen(
            screenTitle = stringResource(R.string.authentication_register_title),
            mainText = stringResource(R.string.authentication_register),
            sideText = stringResource(R.string.authentication_login),
            navigationText = stringResource(R.string.authentication_already_have_account),
            onTopButtonClicked = {},
            onBottomButtonClicked = {}
        )
    }
}

@Composable
fun AuthenticationScreen(
    screenTitle: String,
    mainText: String,
    sideText: String,
    navigationText: String,
    onTopButtonClicked: () -> Unit,
    onBottomButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.white),
                        colorResource(R.color.light_blue)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        // Title
        Text(
            text = screenTitle,
            fontSize = 24.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Logo
        Image(
            painter = painterResource(R.drawable.logo_central_class_full),
            contentDescription = "Central Class Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Card
        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.gray),
                    shape = RoundedCornerShape(14.dp),
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 32.dp)
            ) {
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var isEmailValid by rememberSaveable { mutableStateOf(true) }
                var isPasswordValid by rememberSaveable { mutableStateOf(true) }
                var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

                // Card Title
                Text(
                    text = mainText,
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { input ->
                        email = input
                        isEmailValid = input.matches(Regex("^[A-Za-z0-9._%+-]+@gmail\\.com$")) // todo
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp),
                    label = {
                        Text(
                            text = stringResource(R.string.email),
                            fontSize = 14.sp
                        )
                    },
                    isError = !isEmailValid && email.isNotEmpty(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white),
                        focusedLabelColor = colorResource(R.color.black),
                        unfocusedLabelColor = colorResource(R.color.black),
                        focusedBorderColor = colorResource(R.color.gray),
                        unfocusedBorderColor = colorResource(R.color.gray),
                        errorBorderColor = colorResource(R.color.red)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                )

                // Email Error
                if (!isEmailValid && email.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.authentication_email_error), // todo
                        color = colorResource(R.color.red),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { input ->
                        if (input.length <= 15) {
                            password = input
                            isPasswordValid = password.length in 6..15 // todo
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp),
                    label = {
                        Text(
                            text = stringResource(R.string.authentication_password),
                            fontSize = 14.sp,
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible },
                            modifier = Modifier.padding(end = 5.dp)
                        ) {
                            Icon(
                                painter = if (isPasswordVisible) {
                                    painterResource(R.drawable.password_show)
                                } else {
                                    painterResource(R.drawable.password_hidden)
                                },
                                contentDescription = if (isPasswordVisible) {
                                    "Hide Password"
                                } else {
                                    "Show Password"
                                }
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    isError = !isPasswordValid && password.isNotEmpty(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white),
                        focusedLabelColor = colorResource(R.color.black),
                        unfocusedLabelColor = colorResource(R.color.black),
                        focusedBorderColor = colorResource(R.color.gray),
                        unfocusedBorderColor = colorResource(R.color.gray),
                        errorBorderColor = colorResource(R.color.red)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                )

                // Password Error
                if (!isPasswordValid && password.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.authentication_password_error), // todo
                        color = colorResource(R.color.red),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Top Button
                TextButton(
                    onClick = onTopButtonClicked,
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_blue)),
                    modifier = Modifier
                        .width(200.dp)
                        .height(45.dp)
                ){
                    Text(
                        text = mainText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.white)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Line
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 3.dp,
                    color = colorResource(R.color.light_gray)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Navigation Text
                Text(
                    text = navigationText,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Bottom Button
                TextButton(
                    onClick = onBottomButtonClicked,
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.very_light_blue)),
                    modifier = Modifier
                        .width(200.dp)
                        .height(45.dp)
                ){
                    Text(
                        text = sideText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.white)
                    )
                }
            }
        }
    }
}