package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.ui.BaseScreen
import com.example.proyekakhirpemrogramanmobile.ui.LoginScreen
import com.example.proyekakhirpemrogramanmobile.ui.RegisterScreen
import com.example.proyekakhirpemrogramanmobile.ui.theme.ProyekAkhirPemrogramanMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ProyekAkhirPemrogramanMobileTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "base_screen"
    ) {
        composable("base_screen") {
            BaseScreen(
                onRegisterScreenButtonClicked = { navController.navigate("register_screen") },
                onLoginScreenButtonClicked = { navController.navigate("login_screen") }
            )
        }
        composable("register_screen") {
            RegisterScreen(
                onLoginScreenButtonClicked = { navController.navigate("login_screen") },
                onBaseScreenButtonClicked = { navController.popBackStack("base_screen", inclusive = false) }
            )
        }
        composable("login_screen") {
            LoginScreen(
                onRegisterScreenButtonClicked = { navController.navigate("register_screen") },
                onBaseScreenButtonClicked = { navController.popBackStack("base_screen", inclusive = false) }
            )
        }
    }
}