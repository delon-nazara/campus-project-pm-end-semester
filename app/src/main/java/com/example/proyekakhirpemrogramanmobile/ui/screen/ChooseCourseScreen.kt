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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

data class ScheduleModelDummy(
    val courseName: String,
    val courseCode: String,
    val lecturer: String,
    val student: String,
    val quota: String,
    val semester: String
)

//Data Dummy

//val listCourseUmum: List<ScheduleModelDummy> = emptyList()
val listCourseKelasMataKuliah: List<ScheduleModelDummy> = emptyList()

val listCourseUmum = listOf(
    ScheduleModelDummy("IELTS", "#001", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5"),
    ScheduleModelDummy("IELTS", "#543", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5"),
    ScheduleModelDummy("IELTS", "#234", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5")
)

//val listCourseKelasMataKuliah = listOf(
//    ScheduleModelDummy("TOEFL", "#001", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5"),
//    ScheduleModelDummy("TOEFL", "#543", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5"),
//    ScheduleModelDummy("TOEFL", "#234", "Dr. Andri Budiman", "Arkan", "46/60", "Semester 5")
//)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChooseCourseScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BarPilihKelas()

        KelasUmum(modifier = Modifier.weight(1f))

        KelasMataKuliah(modifier = Modifier.weight(1f))
    }
}

@Composable
fun BarPilihKelas(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.very_light_gray))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.choose_course),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun KelasUmum(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") } // State untuk teks pencarian
    val filteredCourses = listCourseUmum.filter { course ->
        course.courseCode.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
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
                text = stringResource(R.string.course_umum),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        //Search Feature
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.light_blue))
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                tint = colorResource(R.color.black)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.light_blue))
                    .padding(vertical = 5.dp),
                placeholder = { Text(stringResource(R.string.search_course_by_courseid)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.light_blue),
                    unfocusedContainerColor = colorResource(R.color.light_blue),
                    focusedLabelColor = colorResource(R.color.black),
                    unfocusedLabelColor = colorResource(R.color.black),
                )
            )
        }

        // Content
        if (filteredCourses.isEmpty()) {
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
                    painter = painterResource(R.drawable.icon_notfound),
                    contentDescription = "Not Found",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.course_not_found),
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
                items(filteredCourses) { item ->
                    ItemCourseUmum(item)
                }
            }
        }
    }
}

@Composable
fun ItemCourseUmum(item: ScheduleModelDummy) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${item.courseName} ${item.courseCode}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.white)
            )
            Text(
                text = item.lecturer,
                fontSize = 14.sp,
                color = colorResource(R.color.white)
            )
            Text(
                text = item.student,
                fontSize = 14.sp,
                color = colorResource(R.color.white)
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.account_icon),
                    contentDescription = "Quota Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 3.dp),
                    tint = Color.White,
                )
                Text(
                    text = item.quota,
                    fontSize = 14.sp,
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = item.semester,
                fontSize = 14.sp,
                color = colorResource(R.color.white),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun KelasMataKuliah(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") } // State untuk teks pencarian
    val filteredCourses = listCourseKelasMataKuliah.filter { course ->
        course.courseCode.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
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
                text = stringResource(R.string.course_mata_kuliah),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
            )
        }
        //Search feature
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.light_blue))
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                tint = colorResource(R.color.black)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.light_blue))
                    .padding(vertical = 5.dp),
                placeholder = { Text(stringResource(R.string.search_course_by_courseid)) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.light_blue),
                    unfocusedContainerColor = colorResource(R.color.light_blue),
                    focusedLabelColor = colorResource(R.color.black),
                    unfocusedLabelColor = colorResource(R.color.black),
                )
            )
        }

        // Content
        if (filteredCourses.isEmpty()) {
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
                    painter = painterResource(R.drawable.icon_notfound),
                    contentDescription = "Night icon",
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.course_not_found),
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
                items(filteredCourses) { item ->
                    ItemCourseKelasMataKuliah(item)
                }
            }
        }
    }
}

@Composable
fun ItemCourseKelasMataKuliah(item: ScheduleModelDummy) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.very_light_blue),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${item.courseName} ${item.courseCode}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.white)
            )
            Text(
                text = item.lecturer,
                fontSize = 14.sp,
                color = colorResource(R.color.white)
            )
            Text(
                text = item.student,
                fontSize = 14.sp,
                color = colorResource(R.color.white)
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.account_icon),
                    contentDescription = "Quota Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 3.dp),
                    tint = Color.White,
                )
                Text(
                    text = item.quota,
                    fontSize = 14.sp,
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = item.semester,
                fontSize = 14.sp,
                color = colorResource(R.color.white),
                textAlign = TextAlign.End
            )
        }
    }
}