package com.example.proyekakhirpemrogramanmobile.archive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import kotlinx.coroutines.launch

class PengaturanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPengaturan() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                //===============
                // Drawer content
                //===============
                Column(
                    modifier = Modifier.fillMaxSize().background(colorResource(R.color.very_light_blue)),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Column(
                        modifier = Modifier.background(colorResource(R.color.very_light_blue)),
                        verticalArrangement = Arrangement.Center
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (drawerState.isOpen) drawerState.close() else drawerState.open()
                                    }
                                },
                                modifier = Modifier.size(64.dp)
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Filled.Close),
                                    contentDescription = "Localized description",
                                    tint = Color.Black,
                                    modifier = Modifier.size(48.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(50.dp))
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ){
                        //=================================================
                        //Button Beranda untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Beranda */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Beranda",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Jadwal untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Jadwal */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Jadwal",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Mata Kuliah untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for MataKuliah */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Mata Kuliah",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Tugas untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Tugas */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Tugas",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Beranda untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Modul */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Modul",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Informasi untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Informasi*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Informasi",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Alat untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Alat */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Alat",
                                    color = Color.White,
                                )
                            }
                        }
                        //=================================================
                        //Button Pengaturan untuk Navigation Drawer
                        //=================================================
                        TextButton(
                            onClick = { /* Handle click for Pengaturan*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .align(Alignment.Start)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
                            ){
                                Text(
                                    text = "Pengaturan",
                                    color = Color.White,
                                )
                            }
                        }


                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(R.color.very_dark_blue))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        //=================================================
                        //Button Profil paling bawah untuk Navigation Drawer
                        //=================================================
                        Button(onClick = {/* Handle click for Profil di paling bawah*/},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.profil_arkan),
                                    contentDescription = "Profile Image",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(end = 20.dp)
                                        .padding(start = 10.dp)

                                )
                                Column {
                                    Text(
                                        text = "Usama Bin Laden",
                                        color = Color.White,
                                    )
                                    Text(
                                        text = "221401999",
                                        color = Color.White,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
    ) {
        //=================================================
        //Navigasi Top App Bar
        //=================================================
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = colorResource(id = R.color.light_blue)
                    ),
                    title = {
                        Text(
                            text = "Central Class"
                        )
                    },
                    navigationIcon = {
                        //=================================================
                        //Button untuk membuka Drawer
                        //=================================================
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        //=================================================
                        //Button Profil kanan Top App Bar
                        //=================================================
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Account"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }

        ) { contentPadding ->
            IsiPengaturan(contentPadding)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiPengaturan(paddingValues: PaddingValues) {
    var name by remember { mutableStateOf("Delon Nazara") }
    var nim by remember { mutableStateOf("221401073") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )
        //==========================
        //Ribbon
        //==========================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pengaturan",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.padding(5.dp)
        )
        //=========================================
        //Row yang berisi Hai
        //=========================================
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Hai, $name!",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        //=========================================
        //Row yang berisi Image profil
        //=========================================
        Row(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.profil_arkan),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .scale(1f)
            )
        }
        //=========================================
        //Form Nama
        //=========================================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end=16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nama",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF7F9FC),
                    unfocusedContainerColor = Color(0xFFF7F9FC),
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                ),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    containerColor = Color(0xFFF7F9FC),
//                    focusedBorderColor = Color.Gray,
//                    unfocusedBorderColor = Color.LightGray
//                ),
                shape = RoundedCornerShape(8.dp)
            )
        }
        //=========================================
        //Form NIM
        //=========================================
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end=16.dp)
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "NIM",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            OutlinedTextField(
                value = nim,
                onValueChange = { newValue ->
                    if (newValue.length <= 9 && newValue.all { it.isDigit() }) {
                        nim = newValue
                    }
                },
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF7F9FC),
                    unfocusedContainerColor = Color(0xFFF7F9FC),
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                ),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    containerColor = Color(0xFFF7F9FC),
//                    focusedBorderColor = Color.Gray,
//                    unfocusedBorderColor = Color.LightGray
//                ),
                shape = RoundedCornerShape(8.dp)
            )
        }
        //========================================================
        //Row yang berisi 2 tombol yaitu logout dan delete account
        //========================================================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            //=========================================
            //Button Log out
            //=========================================
            TextButton(
                onClick = { /* Handle click for Button Log out Account */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(15.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                ){
                    Text(
                        text = "Log out",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            //=========================================
            //Button Delete
            //=========================================
            TextButton(
                onClick = { /* Handle click for Button Delete Account */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
                shape = RoundedCornerShape(15.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                ){
                    Text(
                        text = "Delete Account",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.padding(vertical = 100.dp)
        )
        //==========================================
        //Row yang berisi button batal dan save
        //==========================================
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            //=========================================
            //Button Batal
            //=========================================
            TextButton(
                onClick = { /* Handle click for Button Batal */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .weight(1f)
                    .padding(vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
                shape = RoundedCornerShape(15.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                ){
                    Text(
                        text = "Batal",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            //=========================================
            //Button Save
            //=========================================
            TextButton(
                onClick = { /* Handle click for Button Save */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 5.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_light_blue)),
                shape = RoundedCornerShape(15.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                ){
                    Text(
                        text = "Save",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainPengaturan() {
    MainPengaturan()
}