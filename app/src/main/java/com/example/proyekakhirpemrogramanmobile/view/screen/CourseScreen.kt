package com.example.proyekakhirpemrogramanmobile.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.listCourse
import com.example.proyekakhirpemrogramanmobile.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.view.component.SideBar
import com.example.proyekakhirpemrogramanmobile.view.component.Title
import com.example.proyekakhirpemrogramanmobile.view.component.TopBar

@Preview
@Composable
fun CourseScreen() {
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
                    .padding(horizontal =  16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Title(title = stringResource(R.string.sidebar_course))
                CourseList()
            }
        }
    }
}

@Composable
fun CourseList() {
    if (listCourse.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.empty_screen_image),
                    contentDescription = "Empty scree image",
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.course_empty),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.gray)
                )
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_light_blue)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_icon),
                    contentDescription = "Add icon",
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = stringResource(R.string.course_take_course),
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listCourse) { item ->
                CourseListItem(item)
            }
        }
    }
}

@Composable
fun CourseListItem(item : CourseModel) {
    Card(
        onClick = {},
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.very_light_blue),
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.course,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white)
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.lecture,
                    fontSize = 14.sp,
                    color = colorResource(R.color.white)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Semester ${item.semester}",
                    fontSize = 14.sp,
                    color = colorResource(R.color.white)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(item.image),
                contentDescription = "Course image",
                modifier = Modifier.sizeIn(maxWidth = 75.dp, maxHeight = 100.dp)
            )
        }
    }
}
