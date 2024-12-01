package com.example.proyekakhirpemrogramanmobile.ui.screen.archive

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StevenTaskGroupScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                //===============
                // Drawer content
                //===============
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.very_light_blue)),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.background(colorResource(R.color.very_light_blue)),
                        verticalArrangement = Arrangement.Center
                    ) {
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
                    ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
                            ) {
                                Text(
                                    text = "Pengaturan",
                                    color = Color.White,
                                )
                            }
                        }


                    }
                    //
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
                        Button(
                            onClick = {/* Handle click for Profil di paling bawah*/ },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
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
        //Navigasi Top MainApp Bar
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
                        //Button Profil kanan Top MainApp Bar
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
            IsiKelompok(contentPadding)
        }
    }
}

@Composable
fun IsiKelompok(paddingValues: PaddingValues) {

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
                text = "Daftar Kelompok",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.padding(15.dp)
        )

        //=================================================================================
        // Cek kondisi jika list group kosong maka akan tampilan image tidak ada kelompok
        //=================================================================================
        if (groups.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_matakuliah_kosong), // Pastikan ada resource icon yang sesuai
                        contentDescription = "Kosong",
                        modifier = Modifier
                            .size(250.dp)
                            .alpha(0.7f)

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Kelompok belum dibentuk",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }

        } else {
            //===================================================
            // LazyColumn untuk daftar anggota
            //===================================================
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(groups) { group ->
                    GroupCard(group)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun GroupCard(group: Group) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.light_blue)
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.very_dark_blue))
            ) {
                Text(
                    text = group.name,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.very_dark_blue))
                ) {
                    Text(
                        text = "Deskripsi",
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                //===================================================
                // Pemanggilan deskripsi untuk LazyColumn
                //===================================================
                Row() {
                    Text(
                        text = group.description,
                        color = Color.Black,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.very_dark_blue))
                ) {
                    Text(
                        text = "Daftar Anggota",
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                //===================================================
                // Pemanggilan data anggota kelompok untuk LazyColumn
                //===================================================
                group.members.forEach { member ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = member.name,
                            color = Color.Black
                        )
                        Text(
                            text = member.id,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

//===================================================
// Data Class untuk Kelompok
//===================================================

data class Group(
    val name: String,
    val description: String,
    val members: List<Member>
)

data class Member(
    val name: String,
    val id: String
)

//===================================================
//List jika tidak ada anggota kelompok ada di list
//===================================================

// val groups = listOf<Group>()

//===================================================
//List jika anggota kelompok ada di list
//===================================================

val groups = listOf(
    Group(
        name = "Syntax Invalid",
        description = ".Edu adalah sebuah aplikasi mobile yang digunakan sebagai sarana berbagi informasi dalam sebuah kelas perkuliahan.",
        members = listOf(
            Member("Iqbal Alwi", "221401005"),
            Member("Muhammad Fariz Arkan", "221401111"),
            Member("Delon Grace Famohouni Nazara", "221401073"),
            Member("Nico Saputra Siringoringo", "221401040"),
            Member("Steven Anthony", "221401031")
        )
    ),
    Group(
        name = "Rider",
        description = "MechaCare. Aplikasi yang memungkinkan Anda memanggil mekanik kapan saja dan menemukan bengkel terdekat dengan cepat dan mudah.",
        members = listOf(
            Member("Alvian", "221401085"),
            Member("Rafli Riza Syahputra Siregar", "221401077"),
            Member("Muhammad Nurhadi Alfayyadah", "221401011"),
            Member("Andika Prayogo", "221401050"),
            Member("Siti Maysi Wulandari", "221401002")
        )
    )
)