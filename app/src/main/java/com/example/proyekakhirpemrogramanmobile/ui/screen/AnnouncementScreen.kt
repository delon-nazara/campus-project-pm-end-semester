package com.example.proyekakhirpemrogramanmobile.ui.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listAnnouncement
import com.example.proyekakhirpemrogramanmobile.data.model.archive.AnnouncementModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun AnnouncementScreen(
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
                userName = userData.userName,
                studentId = userData.studentId,
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
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {
                Title(title = stringResource(R.string.sidebar_announcement))
                AnnouncementList()
            }
        }
    }
}

@Composable
fun AnnouncementList() {
    if (listAnnouncement.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.empty_screen_image),
                contentDescription = "Empty screen image",
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.as_empty),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.gray)
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listAnnouncement) { announcement ->
                AnnouncementListItem(announcement)
            }
        }
    }
}

@Composable
fun AnnouncementListItem(announcement: AnnouncementModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.light_blue)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            ) {
                Text(
                    text = announcement.course,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = announcement.date,
                    fontStyle = FontStyle.Italic
                )
            }
            HorizontalDivider(
                color = colorResource(R.color.black)
            )
            Text(
                text = announcement.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp, bottom = 6.dp)
            )
            Text(
                text = announcement.description,
                textAlign = TextAlign.Justify,
                fontSize = 16.sp
            )
        }
    }
}