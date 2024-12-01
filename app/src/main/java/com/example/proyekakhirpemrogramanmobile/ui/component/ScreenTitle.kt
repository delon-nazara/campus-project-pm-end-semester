package com.example.proyekakhirpemrogramanmobile.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.util.Poppins

@Composable
fun Title(
    title: String
) {
    Surface(
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
//        shadowElevation = 25.dp, todo
        color = colorResource(R.color.very_dark_blue),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(16.dp)
        )
    }
}