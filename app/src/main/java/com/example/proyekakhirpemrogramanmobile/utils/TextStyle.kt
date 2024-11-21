package com.example.proyekakhirpemrogramanmobile.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val onboardingScreenButtonText = TextStyle(
    fontSize = 22.sp,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Center,
)

val onboardingScreenTitle = TextStyle(
    fontSize = 30.sp,
    fontFamily = Poppins,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
)

val onboardingScreenDescription = TextStyle(
    fontSize = 18.sp,
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Center,
)