package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskType
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.formatTimeDifferent
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime

@Preview
@Composable
fun TaskDetailScreen(
    userData: UserModel? = UserModel(),
    selectedTaskId: String = "",
    taskData: List<TaskModel> = emptyList(),
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
                val task = taskData.find { it.taskId == selectedTaskId } ?: TaskModel()

                Title(
                    title = stringResource(R.string.tds_detail)
                )

                DetailTask(
                    task = task
                )
            }
        }
    }
}

@Composable
fun DetailTask(
    task: TaskModel
) {
    val context = LocalContext.current
    val currentMillis = getCurrentMilliseconds()

    val deadlineDate = "${task.deadline["date"]} ${task.deadline["time"]}"
    val deadlineMillis = parseDateAndTime(deadlineDate)
    val deadline = if (deadlineMillis >= currentMillis) {
        "${formatTimeDifferent(deadlineMillis - currentMillis)} lagi"
    } else {
        "${formatTimeDifferent(currentMillis - deadlineMillis)} yang lalu"
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.light_blue)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = task.courseName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = deadline,
                        fontSize = 14.sp
                    )
                }
                Icon(
                    painter = painterResource(
                        when (task.type) {
                            TaskType.PERSONAL.name -> R.drawable.person_icon
                            TaskType.GROUP.name -> R.drawable.group_icon
                            else -> R.drawable.person_icon
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                HorizontalDivider(
                    color = colorResource(R.color.black),
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                Text(
                    text = task.description,
                    textAlign = TextAlign.Justify,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(task.submissionLink))
                    context.startActivity(intent)
                },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_light_blue)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.tds_link)
                )
            }
        }
    }
}