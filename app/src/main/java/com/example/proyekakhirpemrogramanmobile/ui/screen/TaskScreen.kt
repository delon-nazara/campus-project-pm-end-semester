package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listTask
import com.example.proyekakhirpemrogramanmobile.data.model.archive.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.archive.TaskType
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun TaskScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = R.string.sidebar_task

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideBar(
                coroutineScope = coroutineScope,
                drawerState = drawerState,
                selectedMenu = selectedMenu
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState
                )
            }
        ) { contentPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Title(title = stringResource(R.string.sidebar_task))
                TaskTab()
            }
        }
    }
}

@Composable
fun TaskTab() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabList = listOf(
        stringResource(R.string.active_task),
        stringResource(R.string.past_task)
    )

    val listActiveTask by remember {
        derivedStateOf {
            listTask.filter { it.deadline.contains("lagi") } // todo
        }
    }
    val listPastTask by remember {
        derivedStateOf {
            listTask.filter { it.deadline.contains("lalu") } // todo
        }
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
                    TaskNotEmpty(listActiveTask)
                }
            }
            1 -> {
                if (listPastTask.isEmpty()) {
                    TaskEmpty()
                } else {
                    TaskNotEmpty(listPastTask)
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
            .fillMaxWidth()
            .height(300.dp)
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
            text = stringResource(R.string.home_active_task_empty),
            textAlign = TextAlign.Center,
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TaskNotEmpty(task: List<TaskModel>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            )
    ) {
        items(task) { item ->
            TaskItem(item)
        }
    }
}

@Composable
fun TaskItem(item: TaskModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.course,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                painter = painterResource(
                    when (item.type) {
                        TaskType.PERSONAL -> R.drawable.person_icon
                        TaskType.GROUP -> R.drawable.group_icon
                    }
                ),
                contentDescription = "Type icon",
                tint = colorResource(R.color.black),
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.light_green),
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .size(20.dp)
            )
        }

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
                text = "Deadline ${item.deadline}",
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
                text = item.title,
                fontSize = 14.sp,
            )
        }
    }
}