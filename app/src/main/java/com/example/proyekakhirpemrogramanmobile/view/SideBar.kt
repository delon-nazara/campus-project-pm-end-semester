package com.example.proyekakhirpemrogramanmobile.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
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
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.model.listMenu
import com.example.proyekakhirpemrogramanmobile.utils.Poppins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun SideBarPreview() {
    SideBar(
        coroutineScope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(DrawerValue.Open),
        selectedMenu = R.string.sidebar_home
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white))
        )
    }
}

@Composable
fun SideBar(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    selectedMenu: Int,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
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
                                    painter = painterResource(R.drawable.close_icon),
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
                        listMenu.forEach { menu ->
                            MenuItem(
                                menuIcon = menu.icon,
                                menuText = menu.name,
                                isSelected = menu.name == selectedMenu,
                                onClicked = {}
                            )
                        }
                    }

                    // Bottom Menu
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .clickable {  }
                            .background(colorResource(R.color.very_dark_blue))
                    ) {
                        Spacer(modifier = Modifier.width(24.dp))

                        // Profile Picture
                        Icon(
                            painter = painterResource(R.drawable.profile_picture_temp_icon),
                            contentDescription = "Profile picture",
                            tint = colorResource(R.color.white),
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            // Full Name Text
                            Text(
                                text = "Delon Nazara",  // todo
                                fontSize = 16.sp,
                                fontFamily = Poppins,
                                color = colorResource(R.color.white),
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            // Student Id Text
                            Text(
                                text = "221401073", // todo
                                fontSize = 14.sp,
                                fontFamily = Poppins,
                                color = colorResource(R.color.white),
                            )
                        }
                    }
                }
            }
        }
    ) {
        content()
    }
}

@Composable
fun MenuItem(
    menuIcon: Int,
    menuText: Int,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    Spacer(modifier = Modifier.height(5.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClicked() }
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (isSelected) {
                    colorResource(R.color.dark_blue)
                } else {
                    colorResource(R.color.very_light_blue)
                }
            )
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        // Menu Icon
        Icon(
            painter = painterResource(menuIcon),
            contentDescription = "$menuText icon",
            tint = colorResource(R.color.white),
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Menu Text
        Text(
            text = stringResource(menuText),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
    }
}