package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.example.proyekakhirpemrogramanmobile.ui.screen.ChooseCourseScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.CreateCourseScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(this)
        }
    }
}