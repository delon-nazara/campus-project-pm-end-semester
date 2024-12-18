package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.app.DatePickerDialog
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureStatus
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import java.util.Calendar

@Preview
@Composable
fun ScheduleScreen(
    userData: UserModel? = null,
    lectureData: List<LectureModel> = emptyList(),
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(16.dp)
            ) {
                val calendar = remember { Calendar.getInstance() }
                var selectedDate by remember { mutableStateOf(formatDate(calendar.timeInMillis)) }
                val lectureBasedOnDate = lectureData.filter { it.schedule["date"] == selectedDate }

                val datePickerDialog = DatePickerDialog(
                    LocalContext.current,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        calendar.set(selectedYear, selectedMonth, selectedDay)
                        selectedDate = (formatDate(calendar.timeInMillis))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )

                // Title
                Title(
                    title = stringResource(R.string.sb_schedule)
                )

                // Date and Time
                Date(
                    selectedDate = selectedDate,
                    onBackButtonClicked = {
                        calendar.add(Calendar.DAY_OF_MONTH, -1)
                        selectedDate = formatDate(calendar.timeInMillis)
                    },
                    onNextButtonClicked = {
                        calendar.add(Calendar.DAY_OF_MONTH, 1)
                        selectedDate = formatDate(calendar.timeInMillis)
                    },
                    datePickerDialog = datePickerDialog
                )

                // Today Schedule
                Schedule(
                    lectureData = lectureBasedOnDate
                )
            }
        }
    }
}

@Composable
fun Date(
    selectedDate: String,
    onBackButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    datePickerDialog: DatePickerDialog
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { onBackButtonClicked() },
        ) {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Back icon",
                tint = colorResource(R.color.very_dark_blue),
                modifier = Modifier
                    .size(36.dp)
                    .padding(horizontal = 2.dp)
            )
        }

        Button(
            onClick = { datePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = selectedDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(16.dp)
            )
        }

        IconButton(
            onClick = { onNextButtonClicked() },
        ) {
            Icon(
                painter = painterResource(R.drawable.next_icon),
                contentDescription = "Next icon",
                tint = colorResource(R.color.very_dark_blue),
                modifier = Modifier
                    .size(36.dp)
                    .padding(horizontal = 2.dp)
            )
        }
    }
}

@Composable
fun Schedule(
    lectureData: List<LectureModel> = emptyList()
) {
    Column(
        modifier = Modifier.fillMaxSize()
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
                text = stringResource(R.string.ss_title),
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
                    painter = painterResource(R.drawable.vacation_icon),
                    contentDescription = "Night icon",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.ss_empty),
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
                    ScheduleItem(lecture)
                }
            }
        }
    }
}

@Composable
fun ScheduleItem(
    lecture: LectureModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
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
                text = lecture.course,
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