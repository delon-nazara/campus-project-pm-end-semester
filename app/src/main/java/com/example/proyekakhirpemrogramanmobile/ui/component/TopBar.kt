package com.example.proyekakhirpemrogramanmobile.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.data.model.UserModel
import com.example.proyekakhirpemrogramanmobile.data.source.Route
import com.example.proyekakhirpemrogramanmobile.util.Poppins
import com.example.proyekakhirpemrogramanmobile.util.setImageBasedLetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar(
    userData: UserModel = UserModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    navigateTo: (String, Boolean) -> Unit = { _, _ -> }
) {
    CenterAlignedTopAppBar(
        // Title Text
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 20.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        },

        // Menu Icon
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                },
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.menu_icon),
                    contentDescription = "Menu icon",
                    modifier = Modifier.size(28.dp)
                )
            }
        },

        // Profile Picture
        actions = {
            IconButton(
                onClick = { navigateTo(Route.SETTING_SCREEN.name, false) },
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Image(
                    painter = painterResource(setImageBasedLetter(userData.firstLetter)),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
            }
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.white),
            titleContentColor = colorResource(R.color.very_light_blue)
        ),
        modifier = Modifier.shadow(10.dp)
    )
}