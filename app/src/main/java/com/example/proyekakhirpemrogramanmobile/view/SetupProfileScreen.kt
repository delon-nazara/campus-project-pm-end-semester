package com.example.proyekakhirpemrogramanmobile.view

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
import com.example.proyekakhirpemrogramanmobile.utils.Poppins

@Preview
@Composable
fun SetupProfileScreenPreview() {
    SetupProfileScreen(
        screenTitle = stringResource(R.string.authentication_setup_profile_title),
        cardTitle = stringResource(R.string.authentication_setup_profile),
        finishText = stringResource(R.string.authentication_finish),
        onFinishButtonClicked = {},
    )
}

@Composable
fun SetupProfileScreen(
    screenTitle: String,
    cardTitle: String,
    finishText: String,
    onFinishButtonClicked: () -> Unit,
) {
    Box(
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
        // Logo
        Image(
            painter = painterResource(R.drawable.central_class_half_logo),
            contentDescription = "Central Class Logo",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(85.dp)
                .padding(16.dp)
        )

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Title
            Text(
                text = screenTitle,
                fontSize = 24.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black)
            )

            Spacer(modifier = Modifier.height(40.dp))

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
                        .padding(vertical = 32.dp, horizontal = 32.dp)
                ) {
                    val focusManager = LocalFocusManager.current
                    var fullName by rememberSaveable { mutableStateOf("") }
                    var studentId by rememberSaveable { mutableStateOf("") }
                    var isFullNameValid by rememberSaveable { mutableStateOf(true) }
                    var isStudentIdValid by rememberSaveable { mutableStateOf(true) }
                    var selectedGender by remember { mutableStateOf("Jenis Kelamin") }
                    var isGenderExpanded by remember { mutableStateOf(false) }
                    val genderOptions = listOf("Laki-Laki", "Perempuan", "Bola Basket")

                    // Card Title
                    Text(
                        text = cardTitle,
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Full Name Input
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { input ->
                            fullName = input
                            isFullNameValid = input.length in 3..50 // todo
                        },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.authentication_full_name),
                                fontSize = 14.sp
                            )
                        },
                        isError = !isFullNameValid && fullName.isNotEmpty(),
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
                    if (!isFullNameValid && fullName.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.authentication_full_name_error), // todo
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
                        onValueChange = { input ->
                            if (input.length <= 15) {
                                studentId = input
                                isStudentIdValid = studentId.length == 9 // todo
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = stringResource(R.string.authentication_student_id),
                                fontSize = 14.sp,
                            )
                        },
                        isError = !isStudentIdValid && studentId.isNotEmpty(),
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
                    if (!isStudentIdValid && studentId.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.authentication_student_id_error), // todo
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
                            text = selectedGender,
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
                                        Text(text = option)
                                    },
                                    onClick = {
                                        selectedGender = option
                                        isGenderExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Finish Button
                    TextButton(
                        onClick = onFinishButtonClicked,
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_blue)),
                        modifier = Modifier
                            .width(200.dp)
                            .height(45.dp)
                    ){
                        Text(
                            text = finishText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.white)
                        )
                    }
                }
            }
        }
    }
}