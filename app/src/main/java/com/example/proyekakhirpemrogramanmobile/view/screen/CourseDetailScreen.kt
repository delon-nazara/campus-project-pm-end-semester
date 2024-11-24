package com.example.proyekakhirpemrogramanmobile.view.screen

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
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.view.component.SideBar
import com.example.proyekakhirpemrogramanmobile.view.component.Title
import com.example.proyekakhirpemrogramanmobile.view.component.TopBar

@Preview
@Composable
fun CourseDetailScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = R.string.sidebar_course

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
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(title = "Pemrograman Mobile") // todo: change
                DropdownData(
                    title = R.string.cds_general_information,
                    listData = generalInformationData
                )
                DropdownData(
                    title = R.string.cds_lecturer_information,
                    listData = lecturerInformationData
                )
                DropdownData(
                    title = R.string.cds_lecturer_summary,
                    listData = lecturerSummaryData
                )
                DropdownData(
                    title = R.string.cds_homework_list,
                    listData = homeworkListData
                )
                DropdownData(
                    title = R.string.cds_learning_module,
                    listData = learningModuleData
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
                tint = colorResource(R.color.black),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(title),
                fontSize = 15.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.black)
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
                    Spacer(modifier = Modifier.height(12.dp))
                    listData.forEach { data ->
                        CourseData(
                            title = data.key,
                            content = data.value
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseData(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "$title:",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}

// todo: temporary data
val generalInformationData = mapOf(
    "Nama" to "Pemrograman Mobile",
    "Kode" to "ILK3105",
    "SKS" to "3 SKS",
    "Program Studi" to "Ilmu Komputer",
    "Fakultas" to "Ilmu Komputer dan Teknologi Informasi",
    "Semester" to "5",
    "Tahun" to "2024",
    "Dosen" to "Nurrahmadayeni M.Kom",
    "Komting" to "Muhammad Fariz Arkan",
    "Mahasiswa" to "36 Orang",
)

val lecturerInformationData = mapOf(
    "Hari Perkuliahan" to "Kamis",
    "Jam Perkuliahan" to "10.30 - 13.00",
    "Durasi Perkuliahan" to "2 Jam 30 Menit (150 Menit)",
    "Lokasi Perkuliahan" to "Gedung D Fasilkom-TI",
    "Ruangan Perkuliahan" to "Ruangan 104 (Lantai 1)"
)

val loremIpsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
val lecturerSummaryData = mapOf(
    "Pertemuan 1" to loremIpsum,
    "Pertemuan 2" to loremIpsum,
    "Pertemuan 3" to loremIpsum,
    "Pertemuan 4" to "-",
    "Pertemuan 5" to "-",
    "Pertemuan 6" to "-",
    "Pertemuan 7" to "-",
    "Pertemuan 8" to "-",
)

val homeworkListData = mapOf(
    "Tugas 1" to "Membuat aplikasi kalkulator sederhana",
    "Tugas 2" to "Presentasi jenis-jenis sistem operasi",
    "Tugas 3" to "Proyek akhir pembuatan aplikasi mobile",
)

val learningModuleData = mapOf(
    "Modul 1" to "Pengenalan pemrograman mobile.pptx",
    "Modul 2" to "Instalasi android studio.pdf",
    "Modul 3" to "Tutorial jetpack compose untuk pemula.pdf",
)