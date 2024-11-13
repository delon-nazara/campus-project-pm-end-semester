package com.example.proyekakhirpemrogramanmobile.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.proyekakhirpemrogramanmobile.view.SetupProfileScreen
import com.example.proyekakhirpemrogramanmobile.viewmodel.AuthenticationViewModel
import com.example.proyekakhirpemrogramanmobile.viewmodel.DatabaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun App(context: Context) {
    val authenticationViewModel: AuthenticationViewModel = viewModel()
    val userState by authenticationViewModel.userState.collectAsState()

    val databaseViewModel: DatabaseViewModel = viewModel()

    val navController: NavHostController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = "base_screen"
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
                            val registerResult = authenticationViewModel.register(email, password)
                            if (registerResult == "Successful") {
                                val saveUserResult = databaseViewModel.saveUserToDatabase(userState!!.uid)
                                if (saveUserResult == "Successful") {
                                    showToast(context, "Successful")
                                    navController.navigate("setup_profile_screen") {
                                        popUpTo(0) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    showToast(context, saveUserResult)
                                }
                            } else {
                                showToast(context, registerResult)
                            }
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
                            val result = authenticationViewModel.login(email, password)
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
        composable("setup_profile_screen") {
            SetupProfileScreen(
                onSetupProfileButtonClicked = { fullName, studentId ->

                }
            )
        }
        composable("home_screen") {
            HomeScreen(
                email = userState?.email,
                onLogoutButtonClicked = {
                    authenticationViewModel.logout()
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