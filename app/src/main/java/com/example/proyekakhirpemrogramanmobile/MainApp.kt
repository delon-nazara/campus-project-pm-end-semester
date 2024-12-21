package com.example.proyekakhirpemrogramanmobile

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.data.source.Route
import com.example.proyekakhirpemrogramanmobile.ui.screen.AdminDetailScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.AdminScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.AnnouncementScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.CourseManageScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.CourseDetailScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.CourseScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.CourseCreateScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.HomeScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.LoginScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.ModuleDetailScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.ModuleScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.OnboardingScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.RegisterScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.ScheduleScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.SettingScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.SetupProfileScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.TaskDetailScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.TaskScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.ToolScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.temp.SpinWheelTool
import com.example.proyekakhirpemrogramanmobile.util.showToast
import com.example.proyekakhirpemrogramanmobile.viewmodel.AuthenticationViewModel
import com.example.proyekakhirpemrogramanmobile.viewmodel.DatabaseViewModel
import com.example.proyekakhirpemrogramanmobile.viewmodel.LoadingViewModel

@Composable
fun MainApp(context: Context) {
    val authenticationViewModel: AuthenticationViewModel = viewModel()
    val userAuthState by authenticationViewModel.userAuthState.collectAsState()
    val errorEmailState by authenticationViewModel.errorEmailState.collectAsState()
    val errorPasswordState by authenticationViewModel.errorPasswordState.collectAsState()
    val errorFullNameState by authenticationViewModel.errorFullNameState.collectAsState()
    val errorStudentIdState by authenticationViewModel.errorStudentIdState.collectAsState()
    val errorGenderState by authenticationViewModel.errorGenderState.collectAsState()
    val errorAllState by authenticationViewModel.errorAllState.collectAsState()

    val databaseViewModel: DatabaseViewModel = viewModel()
    val userState by databaseViewModel.userState.collectAsState()
    val lectureState by databaseViewModel.lectureState.collectAsState()
    val courseState by databaseViewModel.courseState.collectAsState()
    val allCourseState by databaseViewModel.allCourseState.collectAsState()
    val taskState by databaseViewModel.taskState.collectAsState()
    val moduleState by databaseViewModel.moduleState.collectAsState()
    val announcementState by databaseViewModel.announcementState.collectAsState()
    val selectedCourseIdState by databaseViewModel.selectedCourseIdState.collectAsState()
    val selectedTaskIdState by databaseViewModel.selectedTaskIdState.collectAsState()

    val loadingViewModel: LoadingViewModel = viewModel()
    val loadingState by loadingViewModel.loadingState.collectAsState()

    val navController: NavHostController = rememberNavController()
    val navigateTo: (String, Boolean) -> Unit = { route, clearStack ->
        navController.navigate(route) {
            if (clearStack) {
                popUpTo(0) {
                    inclusive = true
                }
            } else {
                popUpTo(route) {
                    inclusive = true
                }
            }
        }
    }

    val startDestination = Route.ONBOARDING_SCREEN.name

    LaunchedEffect(Unit) {
        if (userAuthState != null) {
            databaseViewModel.checkUserFromDatabase(
                userId = userAuthState!!.uid,
                showLoading = { state ->
                    loadingViewModel.showLoading(state)
                },
                onUserExist = {
                    databaseViewModel.getUserFromDatabase(
                        userId = userAuthState!!.uid,
                        showLoading = { state ->
                            loadingViewModel.showLoading(state)
                        },
                        onSuccess = {
                            navigateTo(Route.HOME_SCREEN.name, true)
                        },
                        onFailure = {
                            showToast(context, "Proses masuk gagal, coba kembali")
                        }
                    )
                },
                onUserNotExist = {
                    authenticationViewModel.clearAllErrorState()
                    navigateTo(Route.SETUP_PROFILE_SCREEN.name, false)
                },
                onFailure = {
                    showToast(context, "Proses masuk gagal, coba kembali")
                }
            )
        }
    }

    NavHost(
        navController = navController,
        startDestination =  startDestination
    ) {
        // Route Onboarding Screen
        composable(Route.ONBOARDING_SCREEN.name) {
            OnboardingScreen(
                loadingState = loadingState,
                onStartButtonClicked = {
                    navigateTo(Route.LOGIN_SCREEN.name, false)
                }
            )
        }

        // Route Login Screen
        composable(Route.LOGIN_SCREEN.name) {
            LoginScreen(
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState,
                errorAllState = errorAllState,
                loadingState = loadingState,
                onLoginButtonClicked = { email, password ->
                    authenticationViewModel.login(
                        email = email,
                        password = password,
                        showLoading = { state ->
                            loadingViewModel.showLoading(state)
                        },
                        onSuccess = { userId ->
                            databaseViewModel.checkUserFromDatabase(
                                userId = userId,
                                showLoading = { state ->
                                    loadingViewModel.showLoading(state)
                                },
                                onUserExist = {
                                    databaseViewModel.getUserFromDatabase(
                                        userId = userId,
                                        showLoading = { state ->
                                            loadingViewModel.showLoading(state)
                                        },
                                        onSuccess = {
                                            navigateTo(Route.HOME_SCREEN.name, true)
                                        },
                                        onFailure = {
                                            showToast(context, "Proses masuk gagal, coba kembali")
                                        }
                                    )
                                },
                                onUserNotExist = {
                                    authenticationViewModel.clearAllErrorState()
                                    navigateTo(Route.SETUP_PROFILE_SCREEN.name, false)
                                },
                                onFailure = {
                                    showToast(context, "Proses masuk gagal, coba kembali")
                                }
                            )
                        },
                        onFailure = {
                            showToast(context, "Proses masuk gagal, coba kembali")
                        }
                    )
                },
                onRegisterButtonClicked = {
                    authenticationViewModel.clearAllErrorState()
                    navigateTo(Route.REGISTER_SCREEN.name, false)
                }
            )
        }

        // Route Register Screen
        composable(Route.REGISTER_SCREEN.name) {
            RegisterScreen(
                errorEmailState = errorEmailState,
                errorPasswordState = errorPasswordState,
                loadingState = loadingState,
                onRegisterButtonClicked = { email, password ->
                    authenticationViewModel.register(
                        email = email,
                        password = password,
                        showLoading = { state ->
                            loadingViewModel.showLoading(state)
                        },
                        onSuccess = {
                            authenticationViewModel.clearAllErrorState()
                            navigateTo(Route.SETUP_PROFILE_SCREEN.name, false)
                        },
                        onFailure = {
                            showToast(context, "Pendaftaran gagal, coba kembali")
                        }
                    )
                },
                onLoginButtonClicked = {
                    authenticationViewModel.clearAllErrorState()
                    navigateTo(Route.LOGIN_SCREEN.name, false)
                }
            )
        }

        // Route Setup Profile Screen
        composable(Route.SETUP_PROFILE_SCREEN.name) {
            SetupProfileScreen(
                errorFullNameState = errorFullNameState,
                errorStudentIdState = errorStudentIdState,
                errorGenderState = errorGenderState,
                loadingState = loadingState,
                onFinishButtonClicked = { fullName, studentId, gender ->
                    val fullNameValid = authenticationViewModel.isFullNameInputValid(fullName)
                    val studentIdValid = authenticationViewModel.isStudentIdValid(studentId)
                    val genderValid = authenticationViewModel.isGenderValid(gender)
                    if (fullNameValid && studentIdValid && genderValid) {
                        databaseViewModel.addUserToDatabase(
                            userId = userAuthState!!.uid,
                            email = userAuthState!!.email!!,
                            fullName = fullName,
                            studentId = studentId,
                            gender = gender,
                            showLoading = { state ->
                                loadingViewModel.showLoading(state)
                            },
                            onSuccess = {
                                navigateTo(Route.COURSE_MANAGE_SCREEN.name, true)
                            },
                            onFailure = {
                                showToast(context, "Pendaftaran gagal, coba kembali")
                            }
                        )
                    }
                }
            )
        }

        // Route Choose Course Screen
        composable(Route.COURSE_MANAGE_SCREEN.name) {
            CourseManageScreen(
                userData = userState,
                courseData = allCourseState,
                addCourse = { courseId ->
                    databaseViewModel.addUserCoursesId(courseId)
                },
                deleteCourse = { courseId ->
                    databaseViewModel.deleteUserCoursesId(courseId)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Create Course Screen
        composable(Route.COURSE_CREATE_SCREEN.name) {
            CourseCreateScreen(
                onCancelButtonClicked = {
                    navController.popBackStack()
                },
                onConfirmButtonClicked = { allData ->
                    databaseViewModel.addCourseToDatabase(
                        allData[0],
                        allData[1],
                        allData[2],
                        allData[3],
                        allData[4],
                        allData[5],
                        allData[6],
                        allData[7],
                        allData[8],
                        allData[9],
                        allData[10],
                        allData[11],
                        allData[12],
                    )
                    navigateTo(Route.COURSE_MANAGE_SCREEN.name, false)
                }
            )
        }

        // Route Home Screen
        composable(Route.HOME_SCREEN.name) {
            HomeScreen(
                userData = userState,
                lectureData = lectureState,
                taskData = taskState,
                selectedCourse = { courseId ->
                    databaseViewModel.setSelectedCourseIdState(courseId)
                    navigateTo(Route.COURSE_DETAIL_SCREEN.name, false)
                },
                selectedTask = { taskId ->
                    databaseViewModel.setSelectedTaskIdState(taskId)
                    navigateTo(Route.TASK_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Schedule Screen
        composable(Route.SCHEDULE_SCREEN.name) {
            ScheduleScreen(
                userData = userState,
                lectureData = lectureState,
                selectedCourse = { courseId ->
                    databaseViewModel.setSelectedCourseIdState(courseId)
                    navigateTo(Route.COURSE_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Course Screen
        composable(Route.COURSE_SCREEN.name) {
            CourseScreen(
                userData = userState,
                courseData = courseState,
                selectedCourse = { courseId ->
                    databaseViewModel.setSelectedCourseIdState(courseId)
                    navigateTo(Route.COURSE_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Course Detail Screen
        composable(Route.COURSE_DETAIL_SCREEN.name) {
            CourseDetailScreen(
                userData = userState,
                selectedCourseId = selectedCourseIdState,
                courseData = courseState,
                lectureData = lectureState,
                taskData = taskState,
                moduleData = moduleState,
                announcementData = announcementState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Task Screen
        composable(Route.TASK_SCREEN.name) {
            TaskScreen(
                userData = userState,
                taskData = taskState,
                selectedTask = { taskId ->
                    databaseViewModel.setSelectedTaskIdState(taskId)
                    navigateTo(Route.TASK_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Task Detail Screen
        composable(Route.TASK_DETAIL_SCREEN.name) {
            TaskDetailScreen(
                userData = userState,
                taskData = taskState,
                selectedTaskId = selectedTaskIdState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Module Screen
        composable(Route.MODULE_SCREEN.name) {
            ModuleScreen(
                userData = userState,
                courseData = courseState,
                selectedCourse = { courseId ->
                    databaseViewModel.setSelectedCourseIdState(courseId)
                    navigateTo(Route.MODULE_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                },
            )
        }

        // Route Module Detail Screen
        composable(Route.MODULE_DETAIL_SCREEN.name) {
            ModuleDetailScreen(
                userData = userState,
                selectedCourseId = selectedCourseIdState,
                courseData = courseState,
                moduleData = moduleState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                },
            )
        }

        // Route Announcement Screen
        composable(Route.ANNOUNCEMENT_SCREEN.name) {
            AnnouncementScreen(
                userData = userState,
                selectedCourseId = selectedCourseIdState,
                announcementData = announcementState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Tool Screen
        composable(Route.TOOL_SCREEN.name) {
            ToolScreen(
                userData = userState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Tool Spin Wheel Screen
        composable(Route.TOOL_SPIN_WHEEL_SCREEN.name) {
            Log.d("noled", "called")
            SpinWheelTool()
        }

        // Route Tool Voting Screen

        // Route Admin Screen
        composable(Route.ADMIN_SCREEN.name) {
            AdminScreen(
                userData = userState,
                courseData = courseState,
                selectedCourse = { courseId ->
                    databaseViewModel.setSelectedCourseIdState(courseId)
                    navigateTo(Route.ADMIN_DETAIL_SCREEN.name, false)
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                }
            )
        }

        // Route Admin Detail Screen
        composable(Route.ADMIN_DETAIL_SCREEN.name) {
            AdminDetailScreen(
                userData = userState,
                selectedCourseId = selectedCourseIdState,
                courseData = courseState,
                lectureData = lectureState,
                taskData = taskState,
                moduleData = moduleState,
                announcementData = announcementState,
                onEditButtonClicked = { allData ->
                    databaseViewModel.updateLectureFromDatabase(
                        allData[0],
                        allData[1],
                        allData[2],
                        allData[3],
                        allData[4],
                        allData[5],
                        allData[6],
                        allData[7],
                        allData[8],
                        allData[9],
                    ) },
                onDeleteButtonClicked = { courseId, number ->
                    databaseViewModel.deleteLectureFromDatabase(courseId, number)
                },
                onAddButtonClicked = { allData ->
                    databaseViewModel.addTaskToDatabase(
                        allData[0],
                        allData[1],
                        allData[2],
                        allData[3],
                        allData[4],
                        allData[5],
                        allData[6],
                        allData[7],
                    )
                },
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                },
            )
        }

        // Route Setting Screen
        composable(Route.SETTING_SCREEN.name) {
            SettingScreen(
                userData = userState,
                navigateTo = { route, clearStack ->
                    navigateTo(route, clearStack)
                },
                logout = {
                    authenticationViewModel.logout()
                    databaseViewModel.logout()
                    navigateTo(Route.ONBOARDING_SCREEN.name, true)
                }
            )
        }

    }
}