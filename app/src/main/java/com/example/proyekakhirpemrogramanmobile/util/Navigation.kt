package com.example.proyekakhirpemrogramanmobile.util

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.Route
import com.example.proyekakhirpemrogramanmobile.view.screen.AuthenticationScreen
import com.example.proyekakhirpemrogramanmobile.view.screen.HomeScreen
import com.example.proyekakhirpemrogramanmobile.view.screen.OnboardingScreen
import com.example.proyekakhirpemrogramanmobile.view.screen.SetupProfileScreen

@Composable
fun App() {

//    val authenticationViewModel: AuthenticationViewModel = viewModel()
//    val userState by authenticationViewModel.userState.collectAsState()

//    val databaseViewModel: DatabaseViewModel = viewModel()

    val navController: NavHostController = rememberNavController()
//    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    // todo
//    databaseViewModel.cloudinaryInitialization(context)

    // todo
    val startDestination = "onboarding_screen"

    NavHost(
        navController = navController,
        startDestination =  startDestination
    ) {
        // =============================
        //      FINAL SECTION START
        // =============================

        // Route: Onboarding Screen
        composable(
            route = Route.ONBOARDING_SCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            OnboardingScreen(
                onStartButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name)
                }
            )
        }

        // Route: Login Screen
        composable(
            route = Route.LOGIN_SCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            AuthenticationScreen(
                screenTitle = R.string.authentication_login_title,
                mainText = R.string.authentication_login,
                sideText = R.string.authentication_register,
                navigationText = R.string.authentication_not_have_account,
                onTopButtonClicked = {
                    navController.navigate(Route.HOME_SCREEN.name)
                },
                onBottomButtonClicked = {
                    navController.navigate(Route.REGISTER_SCREEN.name)
                }
            )
        }

        // Route: Register Screen
        composable(
            route = Route.REGISTER_SCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            AuthenticationScreen(
                screenTitle = R.string.authentication_register_title,
                mainText = R.string.authentication_register,
                sideText = R.string.authentication_login,
                navigationText = R.string.authentication_already_have_account,
                onTopButtonClicked = {
                    navController.navigate(Route.SETUP_PROFILE_SCREEN.name)
                },
                onBottomButtonClicked = {
                    navController.navigate(Route.LOGIN_SCREEN.name) {
                        popUpTo(Route.LOGIN_SCREEN.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Route: Setup Profile Screen
        composable(
            route = Route.SETUP_PROFILE_SCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            SetupProfileScreen(
                screenTitle = R.string.setup_profile_title,
                cardTitle = R.string.setup_profile,
                finishText = R.string.setup_profile_finish,
                onFinishButtonClicked = {
                    navController.navigate(Route.HOME_SCREEN.name)
                },
            )
        }

        // Route: Home Screen
        composable(
            route = Route.HOME_SCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(1000)) },
            popEnterTransition = { fadeIn(animationSpec = tween(1000)) },
            exitTransition = { fadeOut(animationSpec = tween(1000)) },
            popExitTransition = { fadeOut(animationSpec = tween(1000)) }
        ) {
            HomeScreen()
        }

        // ===========================
        //      FINAL SECTION END
        // ===========================

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
//        composable("base_screen") {
//            BaseScreen(
//                onRegisterScreenButton = {
//                    navController.navigate("register_screen") {
//                        popUpTo("register_screen") {
//                            inclusive = true
//                        }
//                    }
//                },
//                onLoginScreenButton = {
//                    navController.navigate("login_screen") {
//                        popUpTo("login_screen") {
//                            inclusive = true
//                        }
//                    }
//                }
//            )
//        }
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
//        composable("setup_profile_screen") {
//            SetupProfileScreen(
//                onSetupProfileButtonClicked = { fullName, studentId -> // todo
//                    coroutineScope.launch {
//                        val result = databaseViewModel.addUserToDatabase(userState!!, fullName, studentId)
//                        if (result == "Successful") {
//                            navController.navigate("home_screen") {
//                                popUpTo(0) {
//                                    inclusive = true
//                                }
//                            }
//                        }
//                        showToast(context, result)
//                    }
//                }
//            )
//        }
//        composable("home_screen") {
//            HomeScreen(
//                imageUrl = databaseViewModel.getImageUrlFromCloudinary(), // todo
//                email = userState?.email,
//                onLogoutButtonClicked = {
//                    authenticationViewModel.logout()
//                    navController.navigate("base_screen") {
//                        popUpTo(0) {
//                            inclusive = true
//                        }
//                    }
//                    showToast(context, "Successful")
//                }
//            )
//        }
    }

}