package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.util.Poppins

@Preview
@Composable
fun LoginScreen(
    errorEmailState: String? = null,
    errorPasswordState: String? = null,
    errorAllState: String? = null,
    loadingState: Boolean = false,
    onLoginButtonClicked: (String, String) -> Unit = { _, _ -> },
    onRegisterButtonClicked: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
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
                text = stringResource(R.string.login_title),
                fontSize = 24.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Logo
            Image(
                painter = painterResource(R.drawable.central_class_full_logo),
                contentDescription = "Central class logo",
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
                    val focusManager = LocalFocusManager.current
                    var email by rememberSaveable { mutableStateOf("") }
                    var password by rememberSaveable { mutableStateOf("") }
                    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

                    // Card Title
                    Text(
                        text = stringResource(R.string.login),
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    // Email Input
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it},
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.email),
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        },
                        isError = errorEmailState != null || errorAllState != null,
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
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    )

                    // Email Error
                    if (errorEmailState != null) {
                        Text(
                            text = errorEmailState,
                            color = colorResource(R.color.red),
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    // Password Input
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it},
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.password),
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { isPasswordVisible = !isPasswordVisible },
                                modifier = Modifier.padding(end = 5.dp)
                            ) {
                                Icon(
                                    painter = if (isPasswordVisible) {
                                        painterResource(R.drawable.password_show_icon)
                                    } else {
                                        painterResource(R.drawable.password_hidden_icon)
                                    },
                                    contentDescription = if (isPasswordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    }
                                )
                            }
                        },
                        visualTransformation = if (isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        isError = errorPasswordState != null || errorAllState != null,
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
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    )

                    // Password Error
                    if (errorPasswordState != null || errorAllState != null) {
                        Text(
                            text = errorPasswordState ?: errorAllState!!,
                            color = colorResource(R.color.red),
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    // Top Button
                    TextButton(
                        onClick = { onLoginButtonClicked(email, password) },
                        enabled = !loadingState,
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
                        modifier = Modifier
                            .width(200.dp)
                            .height(45.dp)
                    ){
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.white)
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    // Line Divider
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 3.dp,
                        color = colorResource(R.color.light_gray)
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    // Navigation Text
                    Text(
                        text = stringResource(R.string.not_have_account),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    // Bottom Button
                    TextButton(
                        onClick = onRegisterButtonClicked,
                        enabled = !loadingState,
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.very_light_blue)),
                        modifier = Modifier
                            .width(200.dp)
                            .height(45.dp)
                    ){
                        Text(
                            text = stringResource(R.string.register),
                            fontSize = 18.sp,
                            fontFamily = Poppins,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.white)
                        )
                    }
                }
            }
        }
        if (loadingState) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}