package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.AnnouncementModel
import com.example.proyekakhirpemrogramanmobile.data.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureStatus
import com.example.proyekakhirpemrogramanmobile.data.model.ModuleModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.ClassStatus
import com.example.proyekakhirpemrogramanmobile.data.source.CourseInfo
import com.example.proyekakhirpemrogramanmobile.data.source.CourseModul
import com.example.proyekakhirpemrogramanmobile.data.source.CourseTask
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.data.source.listClassStatus
import com.example.proyekakhirpemrogramanmobile.data.source.listInfoDetails
import com.example.proyekakhirpemrogramanmobile.data.source.listModulDetails
import com.example.proyekakhirpemrogramanmobile.data.source.listTaskDetails
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatTime
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime
import java.util.Calendar

@Preview
@Composable
fun AdminDetailScreen(
    userData: UserModel? = null,
    selectedCourseId: String = "",
    courseData: List<CourseModel> = emptyList(),
    lectureData: List<LectureModel> = emptyList(),
    taskData: List<TaskModel> = emptyList(),
    moduleData: List<ModuleModel> = emptyList(),
    announcementData: List<AnnouncementModel> = emptyList(),
    onEditButtonClicked: (List<String>) -> Unit = {},
    onDeleteButtonClicked: (String, String) -> Unit = { _, _ ->},
    onAddButtonClicked: (List<String>) -> Unit = {},
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.ADMIN

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
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 0.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                val course = courseData
                    .find { it.courseId == selectedCourseId } ?: CourseModel()
                val lecture = lectureData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { it.number.toInt() }
                val task = taskData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }
                val module = moduleData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }
                val announcement = announcementData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }

                Title(
                    title = course.courseName
                )

                LectureCard(
                    title = R.string.adds_lecture,
                    lectureData = lecture,
                    emptyText = R.string.adds_lecture_empty,
                    onEditButtonClicked = onEditButtonClicked,
                    onDeleteButtonClicked = onDeleteButtonClicked,
                )

                TaskCard(
                    title = R.string.adds_task,
                    taskData = task,
                    courseData = course,
                    emptyText = R.string.adds_task_empty,
                    onAddButtonClicked = onAddButtonClicked,
                )

                ModuleCard(
                    title = R.string.adds_module,
                    moduleData = module,
                    emptyText = R.string.adds_module_empty
                )

                AnnouncementCard(
                    title = R.string.adds_announcement,
                    announcementData = announcement,
                    emptyText = R.string.adds_announcement_empty
                )
            }
        }
    }
}

@Composable
fun LectureCard(
    title: Int,
    onEditButtonClicked: (List<String>) -> Unit,
    onDeleteButtonClicked: (String, String) -> Unit = { _, _ ->},
    lectureData: List<LectureModel>,
    emptyText: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(start = 24.dp, end = 12.dp)
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = stringResource(title),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.circle_add_icon),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

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
                Image(
                    painter = painterResource(R.drawable.empty_screen_image),
                    contentDescription = "Night icon",
                    modifier = Modifier.width(96.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(emptyText),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(lectureData) { lecture ->
                    LectureCardItem(
                        lecture = lecture,
                        onEditButtonClicked = onEditButtonClicked,
                        onDeleteButtonClicked = onDeleteButtonClicked
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LectureCardItem(
    lecture: LectureModel,
    onEditButtonClicked: (List<String>) -> Unit,
    onDeleteButtonClicked: (String, String) -> Unit = { _, _ ->},
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Pertemuan ${lecture.number}",
                fontSize = 16.sp,
                fontWeight = Bold
            )
            Text(
                text = "${lecture.schedule["date"]}",
                fontSize = 14.sp,
            )

        }
        Icon(
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = null,
            tint = colorResource(R.color.blue),
            modifier = Modifier
                .padding(end = 12.dp)
                .size(26.dp)
                .clickable { showEditDialog = true }
        )
        Icon(
            painter = painterResource(R.drawable.delete_icon),
            contentDescription = null,
            tint = colorResource(R.color.red),
            modifier = Modifier
                .size(26.dp)
                .clickable { showDeleteDialog = true }
        )
    }

    if (showEditDialog) {
        var meeting by rememberSaveable { mutableStateOf(lecture.number) }
        var status by remember { mutableStateOf(lecture.status) }
        var notes by rememberSaveable { mutableStateOf(lecture.notes) }
        var summary by rememberSaveable { mutableStateOf(lecture.summary) }
        var building by rememberSaveable { mutableStateOf(lecture.location["building"] ?: "unknown") }
        var floor by rememberSaveable { mutableStateOf(lecture.location["floor"] ?: "unknown") }
        var room by rememberSaveable { mutableStateOf(lecture.location["room"] ?: "unknown") }

        val startDateCalendar = remember { Calendar.getInstance() }
        var startDate by remember { mutableStateOf(lecture.schedule["date"] ?: formatDate(startDateCalendar.timeInMillis)) }
        val startDatePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, selectedYear, selectedMonth, selectedDay ->
                startDateCalendar.set(selectedYear, selectedMonth, selectedDay)
                startDate = formatDate(startDateCalendar.timeInMillis)
            },
            startDateCalendar.get(Calendar.YEAR),
            startDateCalendar.get(Calendar.MONTH),
            startDateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        val time = lecture.schedule["time"]?.split(" - ")

        val startTimeCalendar = remember { Calendar.getInstance() }
        var startTime by remember { mutableStateOf(time?.get(0) ?: formatTime(startTimeCalendar.timeInMillis)) }
        val startTimePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, selectedHour, selectedMinute ->
                startTimeCalendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                startTimeCalendar.set(Calendar.MINUTE, selectedMinute)
                startTime = formatTime(startTimeCalendar.timeInMillis)
            },
            startTimeCalendar.get(Calendar.HOUR_OF_DAY),
            startTimeCalendar.get(Calendar.MINUTE),
            true
        )

        val endTimeCalendar = remember { Calendar.getInstance() }
        var endTime by remember { mutableStateOf(time?.get(1) ?: formatTime(endTimeCalendar.timeInMillis)) }
        val endTimePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, selectedHour, selectedMinute ->
                endTimeCalendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                endTimeCalendar.set(Calendar.MINUTE, selectedMinute)
                endTime = formatTime(endTimeCalendar.timeInMillis)
            },
            endTimeCalendar.get(Calendar.HOUR_OF_DAY),
            endTimeCalendar.get(Calendar.MINUTE),
            true
        )

        BasicAlertDialog(
            onDismissRequest = { showEditDialog = false }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(26.dp)
            ) {
                Text(
                    text = stringResource(R.string.adds_edit_title),
                    fontSize = 18.sp,
                    fontWeight = Bold
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextInputEdit(
                        value = meeting,
                        onValueChange = { meeting = it },
                        label = R.string.adds_lecture_edit_meeting,
                        keyboardType = KeyboardType.Number
                    )
                    SingleChoiceSegmentedButtonRow {
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3),
                            onClick = { status = "PRESENT" },
                            selected = "PRESENT" == status
                        ) {
                            Text("Present")
                        }
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3),
                            onClick = { status = "UNKNOWN" },
                            selected = "UNKNOWN" == status
                        ) {
                            Text("Unknown")
                        }
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3),
                            onClick = { status = "CANCELLED" },
                            selected = "CANCELLED" == status
                        ) {
                            Text("Cancelled")
                        }
                    }
                    TextInputEdit(
                        value = notes,
                        onValueChange = { notes = it },
                        label = R.string.adds_lecture_edit_notes,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 6.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.crc_input_start_date),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = startDate,
                                fontSize = 14.sp
                            )
                        }
                        IconButton(
                            onClick = { startDatePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next_icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 6.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.crc_input_start_time),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = startTime,
                                fontSize = 14.sp
                            )
                        }
                        IconButton(
                            onClick = { startTimePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next_icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 6.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.crc_input_end_time),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = endTime,
                                fontSize = 14.sp
                            )
                        }
                        IconButton(
                            onClick = { endTimePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next_icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    TextInputEdit(
                        value = building,
                        onValueChange = { building = it },
                        label = R.string.adds_lecture_edit_building,
                    )
                    TextInputEdit(
                        value = floor,
                        onValueChange = { floor = it },
                        label = R.string.adds_lecture_edit_floor,
                        keyboardType = KeyboardType.Number
                    )
                    TextInputEdit(
                        value = room,
                        onValueChange = { room = it },
                        label = R.string.adds_lecture_edit_room,
                    )
                    TextInputEdit(
                        value = summary,
                        onValueChange = { summary = it },
                        label = R.string.adds_lecture_edit_summary,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            showEditDialog = false
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_dark_blue)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_no),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                    Button(
                        onClick = {
                            showEditDialog = false

                            val allData = listOf(
                                lecture.courseId,
                                meeting,
                                status,
                                notes,
                                summary,
                                building,
                                floor,
                                room,
                                startDate,
                                "$startTime - $endTime"
                            )

                            onEditButtonClicked(allData)
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_light_blue)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_edit),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDeleteDialog = false }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.adds_delete),
                    fontSize = 16.sp,
                    fontWeight = Bold,
                    textAlign = TextAlign.Center
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(
                        onClick = {
                            showDeleteDialog = false
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_dark_blue)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_no),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                    Button(
                        onClick = {
                            showDeleteDialog = false
                            onDeleteButtonClicked(lecture.courseId, lecture.number)
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_light_blue)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_yes),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(
    title: Int,
    courseData: CourseModel,
    onAddButtonClicked: (List<String>) -> Unit,
    taskData: List<TaskModel>,
    emptyText: Int
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(start = 24.dp, end = 12.dp)
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = stringResource(title),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
            )
            IconButton(
                onClick = { showAddDialog = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.circle_add_icon),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(28.dp)
                )
            }
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
                Image(
                    painter = painterResource(R.drawable.empty_screen_image),
                    contentDescription = "Night icon",
                    modifier = Modifier.width(96.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(emptyText),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(taskData) { task ->
                    TaskCardItem(
                        task = task
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        var taskTitle by rememberSaveable { mutableStateOf("") }
        var type by remember { mutableStateOf("PERSONAL") }
        var description by rememberSaveable { mutableStateOf("") }
        var submissionLink by rememberSaveable { mutableStateOf("") }

        val dateCalendar = remember { Calendar.getInstance() }
        var date by remember { mutableStateOf(formatDate(dateCalendar.timeInMillis)) }
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _, selectedYear, selectedMonth, selectedDay ->
                dateCalendar.set(selectedYear, selectedMonth, selectedDay)
                date = formatDate(dateCalendar.timeInMillis)
            },
            dateCalendar.get(Calendar.YEAR),
            dateCalendar.get(Calendar.MONTH),
            dateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        val timeCalendar = remember { Calendar.getInstance() }
        var time by remember { mutableStateOf(formatTime(timeCalendar.timeInMillis)) }
        val timePickerDialog = TimePickerDialog(
            LocalContext.current,
            { _, selectedHour, selectedMinute ->
                timeCalendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                timeCalendar.set(Calendar.MINUTE, selectedMinute)
                time = formatTime(timeCalendar.timeInMillis)
            },
            timeCalendar.get(Calendar.HOUR_OF_DAY),
            timeCalendar.get(Calendar.MINUTE),
            true
        )

        BasicAlertDialog(
            onDismissRequest = { showAddDialog = false }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(26.dp)
            ) {
                Text(
                    text = stringResource(R.string.adds_add_title),
                    fontSize = 18.sp,
                    fontWeight = Bold
                )
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextInputEdit(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = R.string.adds_task_add_title,
                        keyboardType = KeyboardType.Number
                    )
                    SingleChoiceSegmentedButtonRow {
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                            onClick = { type = "PERSONAL" },
                            selected = "PERSONAL" == type
                        ) {
                            Text("Pribadi")
                        }
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                            onClick = { type = "GROUP" },
                            selected = "GROUP" == type
                        ) {
                            Text("Kelompok")
                        }
                    }
                    TextInputEdit(
                        value = description,
                        onValueChange = { description = it },
                        label = R.string.adds_task_add_description,
                    )
                    TextInputEdit(
                        value = submissionLink,
                        onValueChange = { submissionLink = it },
                        label = R.string.adds_task_add_link,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 6.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = "Tanggal Deadline",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = date,
                                fontSize = 14.sp
                            )
                        }
                        IconButton(
                            onClick = { datePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next_icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                            .padding(start = 16.dp, end = 6.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Column {
                            Text(
                                text = "Jam Deadline",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = time,
                                fontSize = 14.sp
                            )
                        }
                        IconButton(
                            onClick = { timePickerDialog.show() }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next_icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            showAddDialog = false
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_dark_blue)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_no),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                    Button(
                        onClick = {
                            showAddDialog = false

                            val allData = listOf(
                                courseData.courseId,
                                courseData.courseName,
                                taskTitle,
                                type,
                                description,
                                submissionLink,
                                date,
                                time
                            )

                            onAddButtonClicked(allData)
                        },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.very_light_blue)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.adds_edit),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(100.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCardItem(
    task: TaskModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp,
                fontWeight = Bold
            )
            Text(
                text = task.deadline["date"] ?: "20 Desember 2024",
                fontSize = 14.sp,
            )

        }
        Icon(
            painter = painterResource(R.drawable.profile_icon),
            contentDescription = null,
            tint = colorResource(R.color.blue),
            modifier = Modifier
                .padding(end = 12.dp)
                .size(26.dp)
                .clickable {}
        )
        Icon(
            painter = painterResource(R.drawable.delete_icon),
            contentDescription = null,
            tint = colorResource(R.color.red),
            modifier = Modifier
                .size(26.dp)
                .clickable {}
        )
    }
}

@Composable
fun ModuleCard(
    title: Int,
    moduleData: List<ModuleModel>,
    emptyText: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(start = 24.dp, end = 12.dp)
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = stringResource(title),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.circle_add_icon),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        if (moduleData.isEmpty()) {
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
                Image(
                    painter = painterResource(R.drawable.empty_screen_image),
                    contentDescription = "Night icon",
                    modifier = Modifier.width(96.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(emptyText),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(moduleData) { module ->
                    // ModuleCardItem
                }
            }
        }
    }
}

@Composable
fun AnnouncementCard(
    title: Int,
    announcementData: List<AnnouncementModel>,
    emptyText: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(start = 24.dp, end = 12.dp)
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = stringResource(title),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.circle_add_icon),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        if (announcementData.isEmpty()) {
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
                Image(
                    painter = painterResource(R.drawable.empty_screen_image),
                    contentDescription = "Night icon",
                    modifier = Modifier.width(96.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(emptyText),
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                items(announcementData) { announcement ->
                    // AnnouncementCardItem
                }
            }
        }
    }
}

@Composable
fun TextInputEdit(
    value: String,
    onValueChange: (String) -> Unit,
    label: Int,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = stringResource(label),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.white),
            unfocusedContainerColor = colorResource(R.color.white),
            focusedTextColor = colorResource(R.color.black),
            unfocusedTextColor = colorResource(R.color.black),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
    )
}