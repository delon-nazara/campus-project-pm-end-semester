package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listTaskDetail
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun TaskDetailScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.SCHEDULE

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
                DetailTask()
            }
        }
    }
}

@Composable
fun DetailTask() {
    Column {
        Row {
            Column {
                Text(
                    text = listTaskDetail["course"]!!
                )
                Text(
                    text = "Ditugaskan pada ${listTaskDetail["assigned"]!!}"
                )
            }
            Column {
                Icon(
                    painter = painterResource(R.drawable.person_icon),
                    contentDescription = "Personal icon",
                )
                Text(
                    text = listTaskDetail["type"]!!
                )
            }
        }
        Column {
            Text(
                text = listTaskDetail["title"]!!
            )
            HorizontalDivider()
            Text(
                text = listTaskDetail["description"]!!
            )
            Row {
                Icon(
                    painter = painterResource(R.drawable.link_icon),
                    contentDescription = "Link icon"
                )
                Text(
                    text = listTaskDetail["url"]!!
                )
            }
        }
        Button(
            onClick = {}
        ) {
            Text(
                text = stringResource(R.string.marked_as_done)
            )
        }
    }
}