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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskType
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.formatTimeDifferent
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime
import kotlinx.coroutines.delay

@Preview
@Composable
fun TaskScreen(
    userData: UserModel? = UserModel(),
    taskData: List<TaskModel> = emptyList(),
    selectedTask: (String) -> Unit = {},
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.TASK

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideBar(
                userData = userData,
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selectedMenu = selectedMenu,
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
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)
            ) {
                Title(
                    title = stringResource(R.string.sb_task)
                )

                TaskTab(
                    taskData = taskData,
                    selectedTask = selectedTask
                )
            }
        }
    }
}

@Composable
fun TaskTab(
    taskData: List<TaskModel> = emptyList(),
    selectedTask: (String) -> Unit = {},
) {
    var currentMilliseconds by remember { mutableLongStateOf(getCurrentMilliseconds()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentMilliseconds = getCurrentMilliseconds()
        }
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabList = listOf(
        stringResource(R.string.ts_active_task),
        stringResource(R.string.ts_past_task)
    )

    val listActiveTask = taskData
        .filter {
            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}") >= currentMilliseconds
        }
        .sortedBy {
            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}")
        }

    val listPastTask = taskData
        .filter {
            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}") < currentMilliseconds
        }
        .sortedByDescending {
            parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}")
        }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            divider = {},
            indicator = {},
            selectedTabIndex = selectedTabIndex,
        ) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTabIndex == index) {
                                colorResource(R.color.white)
                            } else {
                                colorResource(R.color.black)
                            },
                        )
                    },
                    modifier = Modifier
                        .background(
                            color = if (selectedTabIndex == index) {
                                colorResource(R.color.very_light_blue)
                            } else {
                                colorResource(R.color.light_blue)
                            },
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                )
            }
        }

        when (selectedTabIndex) {
            0 -> {
                if (listActiveTask.isEmpty()) {
                    TaskEmpty()
                } else {
                    TaskNotEmpty(
                        taskData = listActiveTask,
                        selectedTask = selectedTask
                    )
                }
            }
            1 -> {
                if (listPastTask.isEmpty()) {
                    TaskEmpty()
                } else {
                    TaskNotEmpty(
                        taskData = listPastTask,
                        selectedTask = selectedTask
                    )
                }
            }
        }
    }
}

@Composable
fun TaskEmpty() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.very_light_blue),
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
            text = stringResource(R.string.ts_task_empty),
            textAlign = TextAlign.Center,
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TaskNotEmpty(
    taskData: List<TaskModel>,
    selectedTask: (String) -> Unit = {},
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            )
    ) {
        items(taskData) { task ->
            TaskItem(
                task = task,
                selectedTask = selectedTask
            )
        }
    }
}

@Composable
fun TaskItem(
    task: TaskModel,
    selectedTask: (String) -> Unit = {},
) {
    val deadlineDate = "${task.deadline["date"]} ${task.deadline["time"]}"
    val deadlineMillis = parseDateAndTime(deadlineDate)
    val currentMillis = getCurrentMilliseconds()

    val deadline = if (deadlineMillis >= currentMillis) {
        "${formatTimeDifferent(deadlineMillis - currentMillis)} lagi"
    } else {
        "${formatTimeDifferent(currentMillis - deadlineMillis)} yang lalu"
    }

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
                text = "Deadline $deadline",
                fontSize = 14.sp,
            )
        }

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