package com.example.proyekakhirpemrogramanmobile.ui.screen.archive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ArkanTaskDetailScreen() {
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
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.very_light_blue)),
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp)
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
            IsiDetailTugas(contentPadding)
        }
    }
}

@Composable
fun IsiDetailTugas(paddingValues: PaddingValues) {
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
                text = "Tugas",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(
            modifier = Modifier.padding(15.dp)
        )
//        KontainerTugas()
        ListInstruksiTugas()
    }
}
//================================================
//Fungsi pemanggil dataclass DetailTugasMatakuliah
//================================================
@Composable
fun ListInstruksiTugas() {
    val detailInstruksiList = getDetailInstruksiTugas()

    LazyColumn(modifier = Modifier
        .padding(16.dp)
    ) {
        items(detailInstruksiList) { detailTugas ->
            InstruksiTugas(detailTugas)
            Spacer(modifier = Modifier.height(16.dp)) // Tambahkan jarak antar item
        }
    }
}

//=============================
//Desain Card Detail Tugas Matakuliah
//=============================
@Composable
fun InstruksiTugas(
    detailTugas: DetailInstruksiTugas,
    modifier: Modifier = Modifier
) {
    var showAlert by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_blue))
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            //==========================
            // Nama Mata Kuliah dan Icon
            //==========================
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
                Text(
                    text = detailTugas.namaMatkul,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF091441)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.tugas_pribadi),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
                Spacer(modifier = Modifier.height(8.dp))
                //====================================
                // Tanggal Ditugaskan dan Dikumpulkan
                //====================================
                Text(
                    text = "Tenggat : ${detailTugas.tanggalDikumpulkan}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "Ditugaskan :  ${detailTugas.tanggalDitugaskan}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Card(
                modifier = Modifier
                    .padding(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                Column(
                    modifier = Modifier
                        .size(width = 150.dp,30.dp)
                ) {
                    Text(
                        text = "Catatan Tugas : ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 10.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(10.dp),
                        thickness = 2.dp,
                        color = colorResource(R.color.very_light_blue),
                    )
                }
                //===============
                // Perintah Tugas
                //===============
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = detailTugas.perintahTugas,
                    color = Color(0xFF1A237E)
                )
                Row(
                    modifier = Modifier.padding(vertical = 16.dp)
                        .padding(end = 16.dp)

                ){
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Menu"
                    )
                    Text(
                        text = detailTugas.linkTerkait,
                        fontSize = 14.sp,
                        color = Color(0xFF4B84FF),
                        modifier = Modifier.clickable { /* Handle link click */ }
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Tombol "Tandai Sebagai Selesai"
            Button(
                onClick = { showAlert = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B84FF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(16.dp)
            ){
                Text(text = "Tandai Sebagai Selesai", color = Color.White)
            }
        }
        // Alert ketika tombol diklik
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                confirmButton = {
                    TextButton(onClick = { showAlert = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Notifikasi") },
                text = { Text("Tugas telah selesai") }
            )
        }
    }
}

data class DetailInstruksiTugas(
    var namaMatkul : String,
    var tanggalDitugaskan : String,
    var tanggalDikumpulkan : String,
    var perintahTugas : String,
    var linkTerkait : String,
    var iconJenisTugas : Int
)

fun getDetailInstruksiTugas(): List<DetailInstruksiTugas>{
    return listOf<DetailInstruksiTugas>(
        DetailInstruksiTugas(
            "Grafika Komputer",
            "Rabu, 20 November 2024",
            "Rabu, 27 November 2024",
            "Buatlah objek 3 dimensi menggunakan library freeglut",
            "https://youtu.be/NH9yuZUrJVc?si=AvlSa91HOEnXXbOK",
            R.drawable.tugas_pribadi
        ),
    )
}