package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.source.genderOptions
import com.example.proyekakhirpemrogramanmobile.util.Poppins

@Preview
@Composable
fun SetupProfileScreen(
    errorFullNameState: String? = null,
    errorStudentIdState: String? = null,
    errorGenderState: String? = null,
    loadingState: Boolean = false,
    onFinishButtonClicked: (String, String, String) -> Unit = { _, _, _ -> },
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Content
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
                text = stringResource(R.string.setup_profile_title),
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
                    .padding(bottom = 32.dp)
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
                        .padding(vertical = 32.dp, horizontal = 32.dp)
                ) {
                    val focusManager = LocalFocusManager.current
                    var fullName by rememberSaveable { mutableStateOf("") }
                    var studentId by rememberSaveable { mutableStateOf("") }
                    var gender by remember { mutableStateOf("Jenis Kelamin") }
                    var isGenderExpanded by remember { mutableStateOf(false) }

                    // Card Title
                    Text(
                        text = stringResource(R.string.setup_profile_subtitle),
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Full Name Input
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.full_name),
                                fontSize = 14.sp
                            )
                        },
                        isError = errorFullNameState != null,
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

                    // Full Name Error
                    if (errorFullNameState != null) {
                        Text(
                            text = errorFullNameState,
                            color = colorResource(R.color.red),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Student Id Input
                    OutlinedTextField(
                        value = studentId,
                        onValueChange = { studentId = it },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.student_id),
                                fontSize = 14.sp,
                            )
                        },
                        isError = errorStudentIdState != null,
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

                    // Student Id Error
                    if (errorStudentIdState != null) {
                        Text(
                            text = errorStudentIdState,
                            color = colorResource(R.color.red),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // Gender Input
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = colorResource(R.color.gray)
                            )
                            .clickable { isGenderExpanded = true }
                    ) {
                        Text(
                            text = gender,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.drop_down_icon),
                            contentDescription = "Dropdown Menu",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 10.dp)
                        )
                        DropdownMenu(
                            expanded = isGenderExpanded,
                            onDismissRequest = { isGenderExpanded = false },
                            modifier = Modifier.background(colorResource(R.color.white))
                        ) {
                            genderOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = option,
                                            fontSize = 14.sp
                                        )
                                    },
                                    onClick = {
                                        gender = option
                                        isGenderExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Gender Input Error
                    if (errorGenderState != null) {
                        Text(
                            text = errorGenderState,
                            color = colorResource(R.color.red),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Finish Button
                    TextButton(
                        onClick = { onFinishButtonClicked(fullName, studentId, gender) },
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
                        modifier = Modifier
                            .width(200.dp)
                            .height(45.dp)
                    ){
                        Text(
                            text = stringResource(R.string.finish),
                            fontSize = 18.sp,
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