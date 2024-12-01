package com.example.proyekakhirpemrogramanmobile.ui.screen.archive

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
fun ArkanCourseScreen() {
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
            IsiTHalamanMataKuliah(contentPadding)
        }
    }
}
@Composable
fun IsiTHalamanMataKuliah(paddingValues: PaddingValues) {

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
                text = "Mata Kuliah",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.padding(15.dp)
        )
        LazyColumnCallerHalamanMataKuliah()
    }
}

// =======================
// Lazy Colum untuk memanggil data class
// =======================
@Composable
fun LazyColumnCallerHalamanMataKuliah() {
    val mylist = getDetailMataKuliah()

//==========================
//CEK KONDISI LIST MATAKULIAH
//===========================
    if (mylist.isEmpty()) {
        // Tampilkan pesan kosong
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
                // Contoh menampilkan ikon gambar "kosong" seperti yang ada pada gambar
                Image(
                    painter = painterResource(R.drawable.icon_matakuliah_kosong), // Pastikan ada resource icon yang sesuai
                    contentDescription = "Kosong",
                    modifier = Modifier.size(250.dp).
                    alpha(0.7f)

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Kamu belum mengambil mata kuliah",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            //==================================================
            // Floating Action Button di bagian bawah layar
            //==================================================
            Surface(
                onClick = {
                    // Tambahkan aksi ketika FAB diklik
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .width(200.dp), // Lebar FAB yang bisa disesuaikan
                shape = RoundedCornerShape(50), // Membuat FAB dengan bentuk bulat
                color = MaterialTheme.colorScheme.primary,
                shadowElevation = 8.dp // Memberikan efek bayangan
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = 12.dp) // Tinggi FAB
                ) {
                    Text(text = "Ambil Mata Kuliah", color = Color.White)
                }
            }
        }
    } else {
        // Tampilkan daftar mata kuliah jika tidak kosong
        LazyColumn {
            items(mylist) { item ->
                CardMataKuliah(item)
            }
        }
    }
}
// =======================
// Kartu Mata Kulilah
// =======================
@Composable
fun CardMataKuliah(item : DetailMataKuliah) {
//    ===============================================
//    Card dapat diklik untuk pindah activity lainnya
//    ===============================================
    Card(
        onClick = {},
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.very_light_blue),
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 15.dp)
            .fillMaxWidth()
            .size(height = 120.dp, width = 100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .padding(bottom = 16.dp)
                .padding(start = 16.dp), // Menambah padding untuk isi card
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = item.namaMataKuliah,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = item.namaKetuaKelas,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_jumlahmahasiswa_),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = item.jumlahMahasiswa,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
                Spacer(
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Semester : ${item.semesterMataKuliah}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.namaMataKuliah,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}

data class DetailMataKuliah(
    var namaMataKuliah : String,
    var namaKetuaKelas : String,
    var jumlahMahasiswa : String,
    var semesterMataKuliah : Int,
    var imageRes : Int,
)

fun getDetailMataKuliah(): List<DetailMataKuliah>{
    return listOf(
        DetailMataKuliah("Pemrograman Mobile", "Steven Anthony", "35",5,
            R.drawable.laptop_icon
        ),
        DetailMataKuliah("Grafika Komputer", "Iqbal Alwi", "29",5, R.drawable.laptop_icon),
        DetailMataKuliah("Algoritma Pemrograman", "Delon Nazara", "78",5,
            R.drawable.laptop_icon
        ),
    )
}