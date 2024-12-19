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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.listSetting
import com.example.proyekakhirpemrogramanmobile.data.model.SettingModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.data.source.Route
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.ui.component.SideBar
import com.example.proyekakhirpemrogramanmobile.ui.component.Title
import com.example.proyekakhirpemrogramanmobile.ui.component.TopBar
import com.example.proyekakhirpemrogramanmobile.util.setImageBasedLetter

@Preview
@Composable
fun SettingScreen(
    userData: UserModel? = UserModel(),
    navigateTo: (String, Boolean) -> Unit = { _, _ -> },
    logout: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedMenu = Menu.SETTING

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
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
                    .padding(contentPadding)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(title = stringResource(R.string.sb_setting))
                Profile(userData = userData)
                MyClass(navigateTo = navigateTo)
                SettingList()
                Logout(logout)
            }
        }
    }
}

@Composable
fun Profile(userData: UserModel?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(setImageBasedLetter(userData?.firstLetter ?: "u")),
            contentDescription = "Profile picture",
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp)
                .size(72.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = userData?.fullName ?: "Unknown User",
                lineHeight = 24.sp,
                fontSize = 20.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = userData?.studentId ?: "000000000",
                fontSize = 16.sp,
                fontFamily = Poppins,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun MyClass(
    navigateTo: (String, Boolean) -> Unit = { _, _ -> },
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { navigateTo(Route.COURSE_MANAGE_SCREEN.name, false) }
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.light_blue),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Icon(
            painterResource(R.drawable.book_icon),
            contentDescription = "Book icon",
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(R.string.sts_my_class),
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
                text = stringResource(R.string.sts_setting),
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
fun Logout(logout: () -> Unit = {}) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.light_blue),
            contentColor = colorResource(R.color.black)
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = { logout() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painterResource(R.drawable.logout_icon),
            contentDescription = "Logout icon",
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
        )
        Text(
            text = stringResource(R.string.sts_logout),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}