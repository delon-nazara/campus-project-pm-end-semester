package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRegister() {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.birulangit),
                        Color.White
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selamat Datang!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top=50.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.logobirusteven),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(14.dp),
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Daftar",
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                    //================================================
                    // TextField input Email
                    //================================================
                    OutlinedTextField(
                        value = email,
                        onValueChange = { input ->
                            email = input
                            // Validasi email agar harus diakhiri @gmail.com
                            isEmailValid = input.matches(Regex("^[A-Za-z0-9._%+-]+@gmail\\.com$"))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = "Email",
                                fontSize = 14.sp,
                            )
                        },
                        isError = !isEmailValid,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F9FC),
                            unfocusedContainerColor = Color(0xFFF7F9FC),
                            focusedBorderColor = if (isEmailValid) Color.Gray else Color.Red,
                            unfocusedBorderColor = if (isEmailValid) Color.LightGray else Color.Red
                        ),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            containerColor = Color(0xFFF7F9FC),
//                            focusedBorderColor = if (isEmailValid) Color.Gray else Color.Red,
//                            unfocusedBorderColor = if (isEmailValid) Color.LightGray else Color.Red
//                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    //================================================
                    // Peringatan agar email diisi sesuai format
                    //================================================
                    if (!isEmailValid && email.isNotEmpty()) {
                        Text(
                            text = "Email harus diakhiri dengan @gmail.com",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //================================================
                    // TextField input Password
                    //================================================
                    OutlinedTextField(
                        value = password,
                        onValueChange = { input ->
                            // Limit input maximum 15 characters
                            if (input.length <= 15) {
                                password = input
                                // Validasi password agar harus minimal 6 karakter dan maksimal 15 karakter (huruf, angka, atau kombinasinya)
                                isPasswordValid = password.matches(Regex("^[A-Za-z0-9]{6,15}$"))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = "Password",
                                fontSize = 14.sp,
                            )
                        },
                        isError = !isPasswordValid,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F9FC),
                            unfocusedContainerColor = Color(0xFFF7F9FC),
                            focusedBorderColor = if (isEmailValid) Color.Gray else Color.Red,
                            unfocusedBorderColor = if (isEmailValid) Color.LightGray else Color.Red
                        ),
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            containerColor = Color(0xFFF7F9FC),
//                            focusedBorderColor = if (isPasswordValid) Color.Gray else Color.Red,
//                            unfocusedBorderColor = if (isPasswordValid) Color.LightGray else Color.Red
//                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    //================================================
                    // Peringatan agar Password diisi sesuai format
                    //================================================
                    if (!isPasswordValid && password.isNotEmpty()) {
                        Text(
                            text = "Password harus minimal 6-15 karakter huruf atau angka",
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .align(Alignment.Start)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    //================================================
                    // Button untuk Daftar
                    //================================================
                    TextButton(
                        onClick = { /* Handle click for Button Daftar */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                            .padding(vertical = 5.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_blue)),
                        shape = RoundedCornerShape(15.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                        ){
                            Text(
                                text = "Daftar",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 3.dp,
                        color = Color.Gray
                    )

                    Text(
                        text = "Sudah punya akun? Masuk sekarang!",
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                    //================================================
                    // Button untuk Masuk
                    //================================================
                    TextButton(
                        onClick = { /* Handle click for Button Masuk */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                            .padding(vertical = 5.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_blue)),
                        shape = RoundedCornerShape(15.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                        ){
                            Text(
                                text = "Masuk",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainRegister() {
    MainRegister()
}