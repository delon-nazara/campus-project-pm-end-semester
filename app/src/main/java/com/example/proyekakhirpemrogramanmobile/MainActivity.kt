package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyekakhirpemrogramanmobile.ui.theme.ProyekAkhirPemrogramanMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //TEMPAT TES ACTIVITY//
//          =====================
            //MainTemplate()
//            MainHalamanTugas()
//            LazyColumnDemo()
//            MainHalamanDetailMataKuliah()
            MainPembuatanKelas()
//          =====================
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Main(){
    Column(
    ){
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Red).weight(1f)
        ){
            Column(
                modifier = Modifier.fillMaxHeight().background(Color.Gray).weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("World")
                Text("Akasha")
            }
            Column(
                modifier = Modifier.fillMaxHeight().background(Color.Magenta).weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("Hello")
                Text("Dummy")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Cyan).weight(1f)
        ){
            Column(
                modifier =Modifier.background(Color.Blue).fillMaxHeight().weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("Hello")
                Text("Hero")
            }

            Column(
                modifier =Modifier.background(Color.Yellow).fillMaxHeight().weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("World")
                Text("Dead")
            }
        }
    }
}
