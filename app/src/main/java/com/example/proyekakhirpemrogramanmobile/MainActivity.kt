package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.ui.theme.ProyekAkhirPemrogramanMobileTheme
import com.example.proyekakhirpemrogramanmobile.view.BaseScreen
import com.example.proyekakhirpemrogramanmobile.view.LoginScreen
import com.example.proyekakhirpemrogramanmobile.view.RegisterScreen
import com.example.proyekakhirpemrogramanmobile.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyekAkhirPemrogramanMobileTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val authViewModel: AuthViewModel = viewModel()
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "base_screen"
    ) {
        composable("base_screen") {
            BaseScreen(
                navController = navController
            )
        }
        composable("register_screen") {
            RegisterScreen(
                authViewModel = authViewModel,
                navController = navController
            )
        }
        composable("login_screen") {
            LoginScreen(
                authViewModel = authViewModel,
                navController = navController
            )
        }
    }
}