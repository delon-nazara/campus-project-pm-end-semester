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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegisterProfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRegisterProfil() {
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var isnimValid by remember { mutableStateOf(true) }

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
                painter = painterResource(R.drawable.logobiru),
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
                        text = "Profil Anda",
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                    //================================================
                    // TextField input Nama
                    //================================================
                    OutlinedTextField(
                        value = nama,
                        onValueChange = { input ->
                            nama = input
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = "Nama",
                                fontSize = 14.sp,
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF7F9FC),
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.LightGray,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    //================================================
                    // TextField input NIM
                    //================================================
                    OutlinedTextField(
                        value = nim,
                        onValueChange = { input ->
                            // Limit input maximum 9 characters
                            if (input.length <= 9) {
                                nim = input
                                // Validasi NIM agar harus 6 karakter dan maksimal 9 angka)
                                isnimValid = nim.matches(Regex("^[0-9]{9}$"))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = {
                            Text(
                                text = "NIM",
                                fontSize = 14.sp,
                            )
                        },
                        isError = !isnimValid,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF7F9FC),
                            focusedBorderColor = if (isnimValid) Color.Gray else Color.Red,
                            unfocusedBorderColor = if (isnimValid) Color.LightGray else Color.Red
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    //================================================
                    // Peringatan agar Password diisi sesuai format
                    //================================================
                    if (!isnimValid && nim.isNotEmpty()) {
                        Text(
                            text = "NIM harus 15 karakter angka",
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
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainRegisterProfil() {
    MainRegisterProfil()
}