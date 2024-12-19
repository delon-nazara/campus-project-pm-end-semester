package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.content.Intent
import android.net.Uri
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
import com.example.proyekakhirpemrogramanmobile.data.model.CourseModel
import com.example.proyekakhirpemrogramanmobile.data.model.ModuleModel
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.parseDateAndTime

@Preview
@Composable
fun ModuleDetailScreen(
    userData: UserModel? = UserModel(),
    selectedCourseId: String = "",
    courseData: List<CourseModel> = emptyList(),
    moduleData: List<ModuleModel> = emptyList(),
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.MODULE

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
            ) {
                val course = courseData
                    .find { it.courseId == selectedCourseId } ?: CourseModel()
                val module = moduleData
                    .filter { it.courseId == selectedCourseId }
                    .sortedBy { parseDateAndTime("${it.created["date"]} ${it.created["time"]}") }

                Title(
                    title = course.courseName
                )

                ModuleDetailList(
                    moduleData = module
                )
            }
        }
    }
}


@Composable
fun ModuleDetailList(
    moduleData: List<ModuleModel> = emptyList(),
) {
    if (moduleData.isEmpty()) {
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
                    text = stringResource(R.string.mds_empty),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.gray)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(moduleData) { module ->
                ModuleDetailListItem(
                    module = module
                )
            }
        }
    }
}

@Composable
fun ModuleDetailListItem(
    module: ModuleModel
) {
    val context = LocalContext.current

    Card(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(module.link))
            context.startActivity(intent)
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.very_light_blue),
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(
                    when (module.type) {
                        "pdf" -> R.drawable.pdf_icon
                        "ppt" -> R.drawable.ppt_icon
                        else -> R.drawable.pdf_icon
                    }
                ),
                contentDescription = "Module icon",
                tint = colorResource(R.color.white),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = module.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.white)
            )
        }
    }
}