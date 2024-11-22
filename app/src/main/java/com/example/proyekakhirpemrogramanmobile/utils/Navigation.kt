package com.example.proyekakhirpemrogramanmobile.utils

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.view.AuthenticationScreen
import com.example.proyekakhirpemrogramanmobile.view.BaseScreen
import com.example.proyekakhirpemrogramanmobile.view.HomeScreen
import com.example.proyekakhirpemrogramanmobile.view.OnboardingScreen
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

    // todo
    databaseViewModel.cloudinaryInitialization(context)

    // todo
    val startDestination = "onboarding_screen"

    NavHost(
        navController = navController,
        startDestination =  startDestination
    ) {
//        coroutineScope.launch {
//            if (userState != null) {
//                if (databaseViewModel.userExistInDatabase(userState!!.uid)) {
//                    navController.navigate("home_screen") {
//                        popUpTo(0) {
//                            inclusive = true
//                        }
//                    }
//                } else {
//                    navController.navigate("setup_profile_screen") {
//                        popUpTo(0) {
//                            inclusive = true
//                        }
//                    }
//                }
//            }
//        }

        // =============================
        //      FINAL SECTION START
        // =============================

        composable(
            route = "onboarding_screen",
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            OnboardingScreen(
                onStartButtonClicked = {
                    navController.navigate("login_screen")
                }
            )
        }

        composable(
            route = "login_screen",
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            AuthenticationScreen(
                screenTitle = stringResource(R.string.authentication_login_title),
                mainText = stringResource(R.string.authentication_login),
                sideText = stringResource(R.string.authentication_register),
                navigationText = stringResource(R.string.authentication_not_have_account),
                onTopButtonClicked = {},
                onBottomButtonClicked = {
                    navController.navigate("register_screen") {
                        popUpTo("register_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "register_screen",
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            AuthenticationScreen(
                screenTitle = stringResource(R.string.authentication_register_title),
                mainText = stringResource(R.string.authentication_register),
                sideText = stringResource(R.string.authentication_login),
                navigationText = stringResource(R.string.authentication_already_have_account),
                onTopButtonClicked = {},
                onBottomButtonClicked = {
                    navController.navigate("login_screen") {
                        popUpTo("login_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ===========================
        //      FINAL SECTION END
        // ===========================

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
//        composable("register_screen") {
//            RegisterScreen(
//                onRegisterButtonClicked = { email, password ->
//                    if (email.isEmpty() or password.isEmpty()) {
//                        showToast(context, "Email or password cannot be empty")
//                    } else {
//                        coroutineScope.launch {
//                            val result = authenticationViewModel.register(email, password)
//                            if (result == "Successful") {
//                                navController.navigate("setup_profile_screen") {
//                                    popUpTo(0) {
//                                        inclusive = true
//                                    }
//                                }
//                            }
//                            showToast(context, result)
//                        }
//                    }
//                },
//                onLoginScreenButtonClicked = {
//                    navController.navigate("login_screen") {
//                        popUpTo("login_screen") {
//                            inclusive = true
//                        }
//                    }
//                },
//                onBaseScreenButtonClicked = {
//                    navController.navigate("base_screen") {
//                        popUpTo("base_screen") {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
//        composable("login_screen") {
//            LoginScreen(
//                onLoginButtonClicked = { email, password ->
//                    if (email.isEmpty() or password.isEmpty()) {
//                        showToast(context, "Email or password cannot be empty")
//                    } else {
//                        coroutineScope.launch {
//                            val result = authenticationViewModel.login(email, password)
//                            if (result == "Successful") {
//                                navController.navigate("home_screen") {
//                                    popUpTo(0) {
//                                        inclusive = true
//                                    }
//                                }
//                            }
//                            showToast(context, result)
//                        }
//                    }
//                },
//                onRegisterScreenButtonClicked = {
//                    navController.navigate("register_screen") {
//                        popUpTo("register_screen") {
//                            inclusive = true
//                        }
//                    }
//                },
//                onBaseScreenButtonClicked = {
//                    navController.navigate("base_screen") {
//                        popUpTo("base_screen") {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
        composable("setup_profile_screen") {
            SetupProfileScreen(
                onSetupProfileButtonClicked = { fullName, studentId -> // todo
                    coroutineScope.launch {
                        val result = databaseViewModel.addUserToDatabase(userState!!, fullName, studentId)
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
            )
        }
        composable("home_screen") {
            HomeScreen(
                imageUrl = databaseViewModel.getImageUrlFromCloudinary(), // todo
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