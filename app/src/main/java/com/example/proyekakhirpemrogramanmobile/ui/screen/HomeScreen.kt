package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureStatus
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskType
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatDay
import com.example.proyekakhirpemrogramanmobile.util.formatDisplayTime
import com.example.proyekakhirpemrogramanmobile.util.formatTimeDifferent
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime
import kotlinx.coroutines.delay

@Preview
@Composable
fun HomeScreen(
    userData: UserModel? = null,
    lectureData: List<LectureModel> = emptyList(),
    taskData: List<TaskModel> = emptyList(),
    selectedCourse: (String) -> Unit = {},
    selectedTask: (String) -> Unit = {},
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.HOME

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideBar(
                userData = userData,
                selectedMenu = selectedMenu,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                navigateTo = navigateTo
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    userData = userData,
                    coroutineScope = coroutineScope,
                    drawerState = drawerState,
                    navigateTo = navigateTo
                )
            }
        ) { contentPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(16.dp)
            ) {
                var currentMilliseconds by remember { mutableLongStateOf(getCurrentMilliseconds()) }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(1000)
                        currentMilliseconds = getCurrentMilliseconds()
                    }
                }

                val currentDay = formatDay(currentMilliseconds)
                val currentDate = formatDate(currentMilliseconds)
                val currentTime = formatDisplayTime(currentMilliseconds)

                // Date and Time
                DateAndTime(
                    currentDay = currentDay,
                    currentDate = currentDate,
                    currentTime = currentTime
                )

                // Today Schedule
                TodaySchedule(
                    lectureData = lectureData
                        .filter {
                            it.schedule["date"] == currentDate
                        }
                        .sortedBy {
                            parseDateAndTime("${it.schedule["date"]} ${it.schedule["time"]}")
                        },
                    selectedCourse = selectedCourse,
                    modifier = Modifier.weight(1f)
                )

                // Active Task
                ActiveTask(
                    taskData = taskData
                        .filter {
                            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}") >= currentMilliseconds
                        }
                        .sortedBy {
                            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}")
                        },
                    selectedTask = selectedTask,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun DateAndTime(
    currentDay: String,
    currentDate: String,
    currentTime: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.very_dark_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Date
        Text(
            text = "$currentDay, $currentDate",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(22.dp)
        )

        // Time
        Text(
            text = currentTime,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.white),
            modifier = Modifier.padding(22.dp)
        )
    }
}

@Composable
fun TodaySchedule(
    lectureData: List<LectureModel> = emptyList(),
    selectedCourse: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = stringResource(R.string.hs_today_schedule),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }

        // Content
        if (lectureData.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.sleep_icon),
                    contentDescription = "Night icon",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.hs_today_schedule_empty),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(lectureData) { lecture ->
                    TodayScheduleItem(
                        lecture = lecture,
                        selectedCourse = selectedCourse
                    )
                }
            }
        }
    }
}

@Composable
fun TodayScheduleItem(
    lecture: LectureModel,
    selectedCourse: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { selectedCourse(lecture.courseId) }
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (lecture.status) {
                        LectureStatus.PRESENT.name -> colorResource(R.color.light_green)
                        LectureStatus.UNKNOWN.name -> colorResource(R.color.light_yellow)
                        LectureStatus.CANCELLED.name -> colorResource(R.color.light_red)
                        else -> colorResource(R.color.light_yellow)
                    },
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = lecture.courseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )
        }

        // Content
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Time
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.time_icon),
                    contentDescription = "Time icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "${lecture.schedule["time"]}",
                    fontSize = 14.sp
                )
            }

            // Location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.location_icon),
                    contentDescription = "Location icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Ruangan ${lecture.location["class"]}",
                    fontSize = 14.sp
                )
            }

            // Notes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.notes_icon),
                    contentDescription = "Notes icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = lecture.notes,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ActiveTask(
    taskData: List<TaskModel> = emptyList(),
    selectedTask: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = stringResource(R.string.hs_active_task),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }

        if (taskData.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.game_icon),
                    contentDescription = "Game icon",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.hs_active_task_empty),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(taskData) { task ->
                    ActiveTaskItem(
                        task = task,
                        selectedTask = selectedTask
                    )
                }
            }
        }
    }
}

@Composable
fun ActiveTaskItem(
    task: TaskModel,
    selectedTask: (String) -> Unit = {},
) {
    val deadlineDate = "${task.deadline["date"]} ${task.deadline["time"]}"
    val deadlineMillis = parseDateAndTime(deadlineDate)
    val currentMillis = getCurrentMilliseconds()
    val deadline = formatTimeDifferent(deadlineMillis - currentMillis)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .clickable { selectedTask(task.taskId) }
    ) {
        // Course
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = task.courseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(
                    when (task.type) {
                        TaskType.PERSONAL.name -> R.drawable.person_icon
                        TaskType.GROUP.name -> R.drawable.group_icon
                        else -> R.drawable.person_icon
                    }
                ),
                contentDescription = "Type icon",
                modifier = Modifier.size(22.dp)
            )
        }

        HorizontalDivider()

        // Deadline
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.time_icon),
                contentDescription = "Time icon",
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Deadline $deadline lagi",
                fontSize = 14.sp,
            )
        }

        // Title
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.notes_icon),
                contentDescription = "Notes icon",
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = task.title,
                fontSize = 14.sp,
            )
        }
    }
}