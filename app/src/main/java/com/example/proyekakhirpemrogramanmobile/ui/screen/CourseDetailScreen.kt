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
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.data.source.archive.generalInformationData
import com.example.proyekakhirpemrogramanmobile.data.source.archive.taskListData
import com.example.proyekakhirpemrogramanmobile.data.source.archive.learningModuleData
import com.example.proyekakhirpemrogramanmobile.data.source.archive.lecturerInformationData
import com.example.proyekakhirpemrogramanmobile.data.source.archive.lecturerSummaryData
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun CourseDetailScreen(navigateTo: (String, Boolean) -> Unit = { _, _ -> }) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.COURSE

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideBar(
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
                    title = R.string.cds_task_list,
                    listData = taskListData
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