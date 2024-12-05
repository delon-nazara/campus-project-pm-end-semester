package com.example.proyekakhirpemrogramanmobile.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Menu
import com.example.proyekakhirpemrogramanmobile.data.source.Route
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.setImageBasedLetter
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun SideBar(
    userData: UserModel = UserModel(),
    selectedMenu: Menu = Menu.HOME,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    ModalDrawerSheet{
        Column(
            modifier = Modifier
                .width(275.dp)
                .fillMaxHeight()
                .background(colorResource(R.color.very_light_blue))
        ){
            // Top Menu
            Surface(
                shadowElevation = 15.dp,
                color = colorResource(R.color.very_light_blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(16.dp))

                    // Close Button
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (drawerState.isOpen) {
                                    drawerState.close()
                                } else {
                                    drawerState.open()
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(setImageBasedLetter(userData.firstLetter)),
                            contentDescription = "Close icon",
                            tint = colorResource(R.color.white),
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Menu Text
                    Text(
                        text = "Menu",
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.white)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // List Menu
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ){
                Menu.entries.forEach { menu ->
                    MenuItem(
                        currentMenu = menu,
                        selectedMenu = selectedMenu,
                        navigateTo = navigateTo
                    )
                }
            }

            // Bottom Menu
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .clickable { navigateTo(Route.SETTING_SCREEN.name, false) }
                    .background(colorResource(R.color.very_dark_blue))
            ) {
                Spacer(modifier = Modifier.width(24.dp))

                // Profile Picture
                Icon(
                    painter = painterResource(R.drawable.person_icon),
                    contentDescription = "Profile picture",
                    tint = colorResource(R.color.white),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    // Full Name Text
                    Text(
                        text = "Delon Nazara",
                        fontSize = 16.sp,
                        fontFamily = Poppins,
                        color = colorResource(R.color.white),
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Student Id Text
                    Text(
                        text = "221401073",
                        fontSize = 14.sp,
                        fontFamily = Poppins,
                        color = colorResource(R.color.white),
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    currentMenu: Menu,
    selectedMenu: Menu,
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    Spacer(modifier = Modifier.height(5.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { navigateTo(currentMenu.destination, false) }
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (currentMenu.title == selectedMenu.title) {
                    colorResource(R.color.dark_blue)
                } else {
                    colorResource(R.color.very_light_blue)
                }
            )
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        // Menu Icon
        Icon(
            painter = painterResource(currentMenu.icon),
            contentDescription = "${currentMenu.title} icon",
            tint = colorResource(R.color.white),
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Menu Text
        Text(
            text = stringResource(currentMenu.title),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
    }
}