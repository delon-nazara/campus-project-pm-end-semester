package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.source.archive.listSetting
import com.example.proyekakhirpemrogramanmobile.data.model.archive.SettingModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar

@Preview
@Composable
fun SettingScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.SCHEDULE

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
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(title = stringResource(R.string.sidebar_setting))
                Profile()
                MyClass()
                SettingList()
                Logout()
            }
        }
    }
}

@Composable
fun Profile() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.person_icon),
            contentDescription = "Profile picture",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(72.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Delon Nazara",
                lineHeight = 24.sp,
                fontSize = 20.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "221401073",
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun MyClass() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Icon(
            painterResource(R.drawable.book_icon),
            contentDescription = "Book icon",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(R.string.ss_my_class),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SettingList() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Text(
                text = stringResource(R.string.ss_setting),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(16.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
        ) {
            listSetting.forEach { setting ->
                SettingListItem(setting)
            }
        }
    }
}

@Composable
fun SettingListItem(setting: SettingModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            painter = painterResource(setting.icon),
            contentDescription = "Setting icon",
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
        )
        Text(
            text = stringResource(setting.name),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painterResource(R.drawable.next_icon),
            contentDescription = "Redirect icon",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun Logout() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.light_blue),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Icon(
            painterResource(R.drawable.logout_icon),
            contentDescription = "Logout icon",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(R.string.logout),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}