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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listTask
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listSchedule
import com.example.proyekakhirpemrogramanmobile.data.model.archive.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.archive.TaskType
import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleModel
import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleStatus
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatTime
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.google.firebase.firestore.core.UserData
import kotlinx.coroutines.delay

@Preview
@Composable
fun HomeScreen(
    userData: UserModel = UserModel(),
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
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(16.dp)
            ) {
                // Date and Time
                DateAndTime()

                // Today Schedule
                TodaySchedule(modifier = Modifier.weight(1f)) // todo

                // Active Task
                ActiveTask(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun DateAndTime() {
    // Time Updater
    var currentMilliseconds by remember { mutableLongStateOf(getCurrentMilliseconds()) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentMilliseconds = getCurrentMilliseconds()
        }
    }

    val currentDate = formatDate(currentMilliseconds)
    val currentTime = formatTime(currentMilliseconds)

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
            text = currentDate,
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
fun TodaySchedule(modifier: Modifier = Modifier) {
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
                text = stringResource(R.string.home_today_schedule),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }

        // Content
        if (listSchedule.isEmpty()) {
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
                    text = stringResource(R.string.home_today_schedule_empty),
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
                items(listSchedule) { item ->
                    TodayScheduleItem(item)
                }
            }
        }
    }
}

@Composable
fun TodayScheduleItem(item: ScheduleModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (item.status) {
                        ScheduleStatus.PRESENT -> colorResource(R.color.light_green)
                        ScheduleStatus.UNKNOWN -> colorResource(R.color.light_yellow)
                        ScheduleStatus.CANCELLED -> colorResource(R.color.light_red)
                    },
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ){
            Text(
                text = item.course,
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
            ){
                Icon(
                    painter = painterResource(R.drawable.time_icon),
                    contentDescription = "Time icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = item.time,
                    fontSize = 14.sp
                )
            }

            // Location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.location_icon),
                    contentDescription = "Location icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = item.location,
                    fontSize = 14.sp
                )
            }

            // Notes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.notes_icon),
                    contentDescription = "Notes icon",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = item.notes,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ActiveTask(modifier: Modifier = Modifier) {
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
                text = stringResource(R.string.home_active_task),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }

        if (listTask.isEmpty()) {
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
                    text = stringResource(R.string.home_active_task_empty),
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
                items(listTask) { item ->
                    ActiveTaskItem(item)
                }
            }
        }
    }
}

@Composable
fun ActiveTaskItem(item: TaskModel) {
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
        // Subject
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
                modifier = Modifier.size(18.dp)
            )
        }

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
                text = "Deadline ${item.deadline}",
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
                text = item.title,
                fontSize = 14.sp,
            )
        }
    }
}