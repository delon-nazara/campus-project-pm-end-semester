package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.AnnouncementModel
import com.example.proyekakhirpemrogramanmobile.data.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.data.model.LectureModel
import com.example.proyekakhirpemrogramanmobile.data.model.ModuleModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime
import com.google.android.play.integrity.internal.s

@Preview
@Composable
fun CourseDetailScreen(
    userData: UserModel? = UserModel(),
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
    val selectedMenu = Menu.COURSE

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
                    .verticalScroll(rememberScrollState())
            ) {
                val course = courseData
                    .find { it.courseId == selectedCourseId } ?: CourseModel()
                val listLectureSummary = lectureData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { it.number.toInt() }
                    .map { it.summary }
                val listTaskTitle = taskData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.deadline["date"]} ${it.deadline["time"]}") }
                    .map { it.title }
                val listModuleTitle = moduleData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }
                    .map { it.title }
                val listAnnouncementTitle = announcementData
                    .filter { it.courseId == "s" }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }
                    .map { it.title }

                Title(
                    title = course.courseName
                )

                DropdownData(
                    title = R.string.cds_general_information,
                    listData = mapOf(
                        "Nama" to course.courseName,
                        "SKS" to "${course.credits} SKS",
                        "Program Studi" to course.major,
                        "Fakultas" to course.faculty,
                        "Semester" to course.semester,
                        "Tahun" to course.year,
                        "Dosen" to course.lecturer,
                        "Komting" to course.leader,
                        "Jumlah Mahasiswa" to "${course.amount["students"]} orang",
                    )
                )

                DropdownData(
                    title = R.string.cds_lecture_information,
                    listData = mapOf(
                        "Hari" to "${course.schedule["day"]}",
                        "Pukul" to "${course.schedule["time"]}",
                        "Gedung" to "${course.location["building"]}",
                        "Lantai" to "${course.location["floor"]}",
                        "Ruangan" to "${course.location["class"]}",
                    )
                )

                DropdownData(
                    title = R.string.cds_lecture_summary,
                    listData = listLectureSummary.mapIndexed { index, value ->
                        "Pertemuan ${index + 1}" to value
                    }.toMap()
                )

                DropdownData(
                    title = R.string.cds_task_list,
                    listData = listTaskTitle.mapIndexed { index, value ->
                        "Tugas ${index + 1}" to value
                    }.toMap()
                )

                DropdownData(
                    title = R.string.cds_learning_module,
                    listData = listModuleTitle.mapIndexed { index, value ->
                        "Modul ${index + 1}" to value
                    }.toMap()
                )

                DropdownData(
                    title = R.string.cds_announcement_list,
                    listData = listAnnouncementTitle.mapIndexed { index, value ->
                        "Pengumuman ${index + 1}" to value
                    }.toMap()
                )
            }
        }
    }
}

@Composable
fun DropdownData(
    title: Int,
    listData: Map<String, String>
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_light_blue),
                    shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                )
                .padding(start = 16.dp)
                .padding(vertical = 12.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_right_icon),
                contentDescription = "arrow icon",
                tint = colorResource(R.color.white),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(title),
                fontSize = 15.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
        }

        AnimatedVisibility(
            visible = isExpanded
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .background(
                            color = colorResource(R.color.white),
                            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                        )
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                    if (listData.isEmpty()) {
                        Text(
                            text = "Tidak ada data yang tersedia",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 14.dp)
                                .padding(bottom = 14.dp)
                        )
                    } else {
                        listData.forEach { data ->
                            CourseData(
                                title = data.key,
                                content = data.value,
                                textAlign = if (title == R.string.cds_lecture_summary) {
                                    TextAlign.Justify
                                } else {
                                    TextAlign.Start
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CourseData(
    title: String,
    content: String,
    textAlign: TextAlign
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "$title:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = content,
            textAlign = textAlign
        )
    }
}