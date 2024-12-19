package com.example.proyekakhirpemrogramanmobile.ui.screen

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
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime

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
                    onAddButtonClicked = {},
                    lectureData = lecture,
                    emptyText = R.string.adds_lecture_empty
                )
//
//                Temp(
//                    title = R.string.adds_task,
//                    onAddButtonClicked = {},
//                    data = emptyList(),
//                    emptyText = R.string.adds_task_empty
//                )
//
//                Temp(
//                    title = R.string.adds_module,
//                    onAddButtonClicked = {},
//                    data = emptyList(),
//                    emptyText = R.string.adds_module_empty
//                )
//
//                Temp(
//                    title = R.string.adds_announcement,
//                    onAddButtonClicked = {},
//                    data = emptyList(),
//                    emptyText = R.string.adds_announcement_empty
//                )
            }
        }
    }
}

@Composable
fun LectureCard(
    title: Int,
    onAddButtonClicked: () -> Unit,
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
                onClick = { onAddButtonClicked() }
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
                        onEditButtonClicked = {},
                        onDeleteButtonClicked = {}
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
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
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
                    text = stringResource(R.string.adds_lecture_edit_title),
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
                    var meeting by rememberSaveable { mutableStateOf("") }
                    var notes by rememberSaveable { mutableStateOf("") }
                    var summary by rememberSaveable { mutableStateOf("") }
                    var building by rememberSaveable { mutableStateOf("") }
                    var floor by rememberSaveable { mutableStateOf("") }
                    var room by rememberSaveable { mutableStateOf("") }

                    TextInputEdit(
                        value = meeting,
                        onValueChange = { meeting = it },
                        label = R.string.adds_lecture_edit_meeting,
                        keyboardType = KeyboardType.Number
                    )
                    // status
                    TextInputEdit(
                        value = notes,
                        onValueChange = { notes = it },
                        label = R.string.adds_lecture_edit_notes,
                    )
                    // schedule
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
                            onEditButtonClicked()
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
                            onDeleteButtonClicked()
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

@Composable
fun ManageCourseStatus(modifier: Modifier = Modifier) {
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
                text = "Pertemuan",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        if (listClassStatus.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )
                    //                Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Tidak ada jadwal Kelas",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                items(listClassStatus) { item ->
                    ManageCourseStatusItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseStatusItem(item: ClassStatus) {
    var editDialog by remember { mutableStateOf(false) }
    var cancelDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = when (item.status) {
                    LectureStatus.PRESENT -> colorResource(R.color.light_green)
                    LectureStatus.UNKNOWN -> colorResource(R.color.light_yellow)
                    LectureStatus.CANCELLED -> colorResource(R.color.light_red)
                },
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.meet,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(

                ) {
                    IconButton(
                        onClick = { editDialog = true },
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.profile_icon),
                            contentDescription = "Edit Class Date",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        )
                    }
                    if (editDialog) {
                        AlertDialog(
                            onDismissRequest = { editDialog = false },
                            containerColor = colorResource(R.color.very_dark_blue),
                            title = {
                                Text(
                                    text = "Ubah Tanggal Pertemuan",
                                    color = colorResource(R.color.white)
                                )
                            },
                            text = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Ubah Pertemuan pada tanggal\n${item.time} ?",
                                    fontSize = 14.sp,
                                    color = colorResource(R.color.white),
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start
                                )
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        editDialog = false
//                                        item.status = LectureStatus.UNKNOWN //gatau ini aku cara ngubah warnanya kalau di edit
                                    },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.white),
                                        contentColor = colorResource(R.color.very_dark_blue),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor = colorResource(R.color.white)
                                    )
                                ) {
                                    Text(
                                        text = "Ubah",
                                    )
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { editDialog = false },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.very_dark_blue),
                                        contentColor = colorResource(R.color.white),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor = colorResource(R.color.white)
                                    )
                                ) {
                                    Text("Batal")
                                }
                            }
                        )
                    }
                    IconButton(
                        onClick = { cancelDialog = true }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close_icon),
                            contentDescription = "Cancel Class Status",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp)
                        )
                    }
                    if (cancelDialog) {
                        AlertDialog(
                            onDismissRequest = { cancelDialog = false },
                            containerColor = colorResource(R.color.very_dark_blue),
                            title = {
                                Text(
                                    text = "Batalkan Pertemuan",
                                    color = colorResource(R.color.white)
                                )
                            },
                            text = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Batalkan Pertemuan pada tanggal\n${item.time} ?",
                                    fontSize = 14.sp,
                                    color = colorResource(R.color.white),
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start
                                )
                            },
                            confirmButton = {
                                Button(
                                    onClick = { cancelDialog = false },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.white),
                                        contentColor = colorResource(R.color.very_dark_blue),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor = colorResource(R.color.white)
                                    )
                                ) {
                                    Text(
                                        text = "Konfirmasi",
                                    )
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { cancelDialog = false },
                                    colors = ButtonColors(
                                        containerColor = colorResource(R.color.very_dark_blue),
                                        contentColor = colorResource(R.color.white),
                                        disabledContainerColor = colorResource(R.color.white),
                                        disabledContentColor = colorResource(R.color.white)
                                    )
                                ) {
                                    Text("Batal")
                                }
                            }
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = when (item.status) {
                        LectureStatus.PRESENT -> "Selesai"
                        LectureStatus.UNKNOWN -> "Diganti"
                        LectureStatus.CANCELLED -> "Batal"
                    }
                )
                Text(
                    text = item.time,
                    fontSize = 14.sp
                )
            }
        }
    }
}

//TUGAS
@Composable
fun ManageCourseTasks(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
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
                text = "Tugas",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Tugas"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listTaskDetails.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                items(listTaskDetails) { item ->
                    ManageCourseTasksItem(item)
                }
            }

        }
    }
}

@Composable
fun ManageCourseTasksItem(item: CourseTask) {
    var editDialog by remember { mutableStateOf(false) }
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = Bold
                )
                Row(
                ) {
                    if (item.taskType) {
                        Image(
                            painter = painterResource(R.drawable.person_icon),
                            contentDescription = null
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.group_icon),
                            contentDescription = null
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (item.status) {
                        Text(
                            text = "Selesai"
                        )
                    } else {
                        Text(
                            text = "Berjalan"
                        )
                    }
                    Text(
                        text = item.time
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { editDialog = true },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.profile_icon),
                        contentDescription = "Edit Class Date",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .size(15.dp)
                    )
                }
                if (editDialog) {
                    AlertDialog(
                        onDismissRequest = { editDialog = false },
                        containerColor = colorResource(R.color.very_dark_blue),
                        title = {
                            Text(
                                text = "Ubah Informasi Tugas",
                                color = colorResource(R.color.white)
                            )
                        },
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Ubah Informasi Tugas\n${item.name} ?",
                                fontSize = 14.sp,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    editDialog = false
//                                        item.status = LectureStatus.UNKNOWN //gatau ini aku cara ngubah warnanya kalau di edit
                                },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.white),
                                    contentColor = colorResource(R.color.very_dark_blue),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor = colorResource(R.color.white)
                                )
                            ) {
                                Text(
                                    text = "Ubah",
                                )
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { editDialog = false },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.very_dark_blue),
                                    contentColor = colorResource(R.color.white),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor = colorResource(R.color.white)
                                )
                            ) {
                                Text("Batal")
                            }
                        }
                    )
                }
                IconButton(
                    onClick = { removeDialog = true }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.close_icon),
                        contentDescription = "Cancel Class Status",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .size(15.dp)
                    )
                }
                if (removeDialog) {
                    AlertDialog(
                        onDismissRequest = { removeDialog = false },
                        containerColor = colorResource(R.color.very_dark_blue),
                        title = {
                            Text(
                                text = "Hapus Tugas",
                                color = colorResource(R.color.white)
                            )
                        },
                        text = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Hapus Tugas\n${item.name} ?",
                                fontSize = 14.sp,
                                color = colorResource(R.color.white),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = { removeDialog = false },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.white),
                                    contentColor = colorResource(R.color.very_dark_blue),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor = colorResource(R.color.white)
                                )
                            ) {
                                Text(
                                    text = "Konfirmasi",
                                )
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { removeDialog = false },
                                colors = ButtonColors(
                                    containerColor = colorResource(R.color.very_dark_blue),
                                    contentColor = colorResource(R.color.white),
                                    disabledContainerColor = colorResource(R.color.white),
                                    disabledContentColor = colorResource(R.color.white)
                                )
                            ) {
                                Text("Batal")
                            }
                        }
                    )
                }
            }
        }
    }
}

//MODUL
@Composable
fun ManageCourseModul(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
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
                text = "Modul",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Modul"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listModulDetails.isEmpty()) {
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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                items(listModulDetails) { item ->
                    ManageCourseModulsItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseModulsItem(item: CourseModul) {
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = item.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
            IconButton(
                onClick = { removeDialog = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Cancel Class Status",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .size(15.dp)
                )
            }
            if (removeDialog) {
                AlertDialog(
                    onDismissRequest = { removeDialog = false },
                    containerColor = colorResource(R.color.very_dark_blue),
                    title = {
                        Text(
                            text = "Hapus Modul?",
                            color = colorResource(R.color.white)
                        )
                    },
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Hapus Modul : \n${item.name}",
                            fontSize = 14.sp,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.very_dark_blue),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor = colorResource(R.color.white)
                            )
                        ) {
                            Text(
                                text = "Konfirmasi",
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.very_dark_blue),
                                contentColor = colorResource(R.color.white),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor = colorResource(R.color.white)
                            )
                        ) {
                            Text("Batal")
                        }
                    }
                )
            }
        }
    }
}

//INFO
@Composable
fun ManageCourseInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
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
                text = "Informasi",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        // Content
        TextButton(
            onClick = {},
        ) {
            Text(
                text = "+ Tambahkan Info"
            )
        }
        HorizontalDivider(
            color = colorResource(R.color.light_gray),
            thickness = 4.dp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        if (listModulDetails.isEmpty()) {
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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sleep_icon),
                        contentDescription = "Night icon",
                        modifier = Modifier.size(48.dp)
                    )

                    Text(
                        text = "Tidak ada Tugas Saat Ini",
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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
                items(listInfoDetails) { item ->
                    ManageCourseInfosItem(item)
                }
            }
        }
    }
}

@Composable
fun ManageCourseInfosItem(item: CourseInfo) {
    var removeDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = item.informasi,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
            IconButton(
                onClick = { removeDialog = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.close_icon),
                    contentDescription = "Cancel Class Status",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .size(15.dp)
                )
            }
            if (removeDialog) {
                AlertDialog(
                    onDismissRequest = { removeDialog = false },
                    containerColor = colorResource(R.color.very_dark_blue),
                    title = {
                        Text(
                            text = "Hapus Informasi?",
                            color = colorResource(R.color.white)
                        )
                    },
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Hapus informasi : \n${item.informasi}",
                            fontSize = 14.sp,
                            color = colorResource(R.color.white),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.white),
                                contentColor = colorResource(R.color.very_dark_blue),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor = colorResource(R.color.white)
                            )
                        ) {
                            Text(
                                text = "Konfirmasi",
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { removeDialog = false },
                            colors = ButtonColors(
                                containerColor = colorResource(R.color.very_dark_blue),
                                contentColor = colorResource(R.color.white),
                                disabledContainerColor = colorResource(R.color.white),
                                disabledContentColor = colorResource(R.color.white)
                            )
                        ) {
                            Text("Batal")
                        }
                    }
                )
            }
        }
    }
}
