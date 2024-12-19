package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Route

@Preview
@Composable
fun CourseManageScreen(
    userData: UserModel? = null,
    courseData: List<CourseModel> = emptyList(),
    addCourse: (String) -> Unit = {},
    deleteCourse: (String) -> Unit = {},
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val course = courseData.sortedBy { it.courseName }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Text(
            text = stringResource(R.string.cm_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.very_dark_blue),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.cm_your_class),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
                )
            }
            val myCourses = course.filter { it.courseId in userData!!.coursesId }

            if (myCourses.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(R.color.light_blue),
                            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.empty_screen_image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .height(72.dp)
                    )
                    Text(
                        text = stringResource(R.string.cm_your_class_empty),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(R.color.light_blue),
                            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    myCourses.forEach { course ->
                        CourseItem(
                            course = course,
                            icon = R.drawable.delete_icon,
                            onClick = { courseId ->
                                deleteCourse(courseId)
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.very_dark_blue),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.cm_available_class),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
                )
            }
            val allCourses = course.filter { it.courseId !in userData!!.coursesId }

            if (allCourses.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(R.color.light_blue),
                            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.empty_screen_image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .height(72.dp)
                    )
                    Text(
                        text = stringResource(R.string.cm_available_class_empty),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(R.color.light_blue),
                            shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                        )
                        .padding(vertical = 20.dp, horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    allCourses.forEach { course ->
                        CourseItem(
                            course = course,
                            icon = R.drawable.circle_add_icon,
                            onClick = { courseId ->
                                addCourse(courseId)
                            }
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { navigateTo(Route.COURSE_CREATE_SCREEN.name, false) },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_light_blue)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.cm_create_class),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { navigateTo(Route.HOME_SCREEN.name, false) },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_dark_blue)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.cm_back),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun CourseItem(
    course: CourseModel,
    icon: Int,
    onClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 20.dp, end = 10.dp)
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = course.courseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = course.lecturer,
                fontSize = 13.sp,
            )
        }
        IconButton(
            onClick = { showDialog = true },
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = colorResource(
                    when (icon) {
                        R.drawable.delete_icon -> R.color.red
                        else -> R.color.light_green
                    }
                ),
                modifier = Modifier.size(28.dp)
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(R.string.cm_confirm)) },
            text = { Text(text = stringResource(R.string.cm_confirm_text)) },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onClick(course.courseId)
                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.very_dark_blue)
                    ),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(text = stringResource(R.string.cm_yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.very_light_blue)
                    ),
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(text = stringResource(R.string.cm_no))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}