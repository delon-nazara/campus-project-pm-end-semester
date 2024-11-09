package com.example.proyekakhirpemrogramanmobile.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.view.BaseScreen
import com.example.proyekakhirpemrogramanmobile.view.HomeScreen
import com.example.proyekakhirpemrogramanmobile.view.LoginScreen
import com.example.proyekakhirpemrogramanmobile.view.RegisterScreen
import com.example.proyekakhirpemrogramanmobile.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun App(context: Context) {
    val authViewModel: AuthViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = authViewModel.startDestinationBasedAuth()
    ) {
        composable("base_screen") {
            BaseScreen(
                onRegisterScreenButton = {
                    navController.navigate("register_screen") {
                        popUpTo("register_screen") {
                            inclusive = true
                        }
                    }
                },
                onLoginScreenButton = {
                    navController.navigate("login_screen") {
                        popUpTo("login_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("register_screen") {
            RegisterScreen(
                onRegisterButtonClicked = { email, password ->
                    if (email.isEmpty() or password.isEmpty()) {
                        showToast(context, "Email or password cannot be empty")
                    } else {
                        coroutineScope.launch {
                            val result = authViewModel.register(email, password)
                            if (result == "Successful") {
                                navController.navigate("home_screen") {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            }
                            showToast(context, result)
                        }
                    }
                },
                onLoginScreenButtonClicked = {
                    navController.navigate("login_screen") {
                        popUpTo("login_screen") {
                            inclusive = true
                        }
                    }
                },
                onBaseScreenButtonClicked = {
                    navController.navigate("base_screen") {
                        popUpTo("base_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("login_screen") {
            LoginScreen(
                onLoginButtonClicked = { email, password ->
                    if (email.isEmpty() or password.isEmpty()) {
                        showToast(context, "Email or password cannot be empty")
                    } else {
                        coroutineScope.launch {
                            val result = authViewModel.login(email, password)
                            if (result == "Successful") {
                                navController.navigate("home_screen") {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            }
                            showToast(context, result)
                        }
                    }
                },
                onRegisterScreenButtonClicked = {
                    navController.navigate("register_screen") {
                        popUpTo("register_screen") {
                            inclusive = true
                        }
                    }
                },
                onBaseScreenButtonClicked = {
                    navController.navigate("base_screen") {
                        popUpTo("base_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("home_screen") {
            HomeScreen(
                email = authViewModel.user?.email,
                onLogoutButtonClicked = {
                    authViewModel.logout()
                    navController.navigate("base_screen") {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                    showToast(context, "Successful")
                }
            )
        }
    }
}