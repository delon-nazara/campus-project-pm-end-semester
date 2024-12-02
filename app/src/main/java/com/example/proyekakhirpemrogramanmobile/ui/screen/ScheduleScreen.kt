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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listSchedule
import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleModel
import com.example.proyekakhirpemrogramanmobile.data.model.archive.ScheduleStatus
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.formatDateWithoutDay
import com.example.proyekakhirpemrogramanmobile.util.getCurrentMilliseconds
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun ScheduleScreen(
    userData: UserModel = UserModel(),
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.SCHEDULE

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
                    .padding(horizontal =  16.dp)
                    .padding(bottom = 16.dp)
            ) {
                // Title
                Title(title = stringResource(R.string.sidebar_schedule))

                // Date and Time
                Date()

                // Today Schedule
                Schedule()
            }
        }
    }
}

@Composable
fun Date() {
    val currentDate = formatDateWithoutDay(getCurrentMilliseconds())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(6.dp))

        // Back Icon
        Icon(
            painter = painterResource(R.drawable.back_icon),
            contentDescription = "Back icon",
            tint = colorResource(R.color.very_dark_blue),
            modifier = Modifier
                .size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Date
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Text(
                text = currentDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Next Icon
        Icon(
            painter = painterResource(R.drawable.next_icon),
            contentDescription = "Back icon",
            tint = colorResource(R.color.very_dark_blue),
            modifier = Modifier
                .size(40.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))
    }
}

@Composable
fun Schedule() {
    Column(
        modifier = Modifier.fillMaxWidth()
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
                text = stringResource(R.string.schedule_title),
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
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.vacation_icon),
                    contentDescription = "Night icon",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.schedule_empty),
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
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(listSchedule) { item ->
                    ScheduleItem(item)
                }
            }
        }
    }
}

@Composable
fun ScheduleItem(item: ScheduleModel) {
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