package com.example.proyekakhirpemrogramanmobile

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class HalamanPembuatanKelas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPembuatanKelas() {
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
            IsiPembuatanKelas(contentPadding)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPembuatanKelas() {
    MainPembuatanKelas()
}
@Composable
fun IsiPembuatanKelas(paddingValues: PaddingValues) {
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
                text = "Pembuatan Kelas",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

//        KontainerTugas()
        BuatKelas()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuatKelas() {
    // State untuk jenis kelas
    var selectedTabIndex by remember { mutableStateOf(0) }

    // State untuk input detail kelas
    var namaKelas by remember { mutableStateOf("") }
    var semester by remember { mutableStateOf("") }
    var namaKelasUmum by remember { mutableStateOf("") }
    var semesterUmum by remember { mutableStateOf("") }
    var namaDosen by remember { mutableStateOf("") }
    var kodeMataKuliah by remember { mutableStateOf("") }
    var jumlahSKS by remember { mutableStateOf("") }
    var hariPerkuliahan by remember { mutableStateOf("") }
    var jamPerkuliahan by remember { mutableStateOf("") }
    var gedungPerkuliahan by remember { mutableStateOf("") }
    var ruanganPerkuliahan by remember { mutableStateOf("") }
    var showWarning by remember { mutableStateOf(false) }
    var showWarningSKS by remember { mutableStateOf(false) }

    // State untuk dialog konfirmasi
    var showDialog by remember { mutableStateOf(false) }
    var showEmptyFieldAlert by remember { mutableStateOf(false) }

    // Scroll state
    val scrollState = rememberScrollState()
    Spacer(
        modifier = Modifier.size(1.dp)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState) // Aktifkan scroll di sini
    ){
        Spacer(
            modifier = Modifier.size(16.dp)
        )
        //===============================
        //Kontainer pemilihan jenis kelas
        //===============================
        Column {
            //===============================
            // TabRow untuk pemilihan halaman
            //===============================
            
            Card{
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = colorResource(R.color.very_light_blue),
                    contentColor = colorResource(R.color.very_dark_blue),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(
                        selected = selectedTabIndex == 0,
                        onClick = { selectedTabIndex = 0 },
                        text = { Text("Kelas Umum", fontWeight = FontWeight.Bold) }
                    )
                    Tab(
                        selected = selectedTabIndex == 1,
                        onClick = { selectedTabIndex = 1 },
                        text = { Text("Kelas Mata Kuliah", fontWeight = FontWeight.Bold) }
                    )
                }
            }
            Spacer(
                modifier = Modifier.size(10.dp)
            )
            //===============================
            // Konten berdasarkan tab yang dipilih
            //===============================
            when (selectedTabIndex) {
                //=======================
                //Pilihan jika kelas Mata Kuliah
                //=======================
                1 -> {
                    Column(

                    ) {
                        Card(
                            Modifier.background(
                                    colorResource(R.color.very_light_blue),
                                    shape = RoundedCornerShape(15.dp)
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(colorResource(R.color.very_light_blue))
                            ) {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(
                                        topStart = 15.dp,
                                        topEnd = 15.dp
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .background(colorResource(R.color.very_dark_blue))
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Detail Kelas",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                                Column(
                                    Modifier.padding(16.dp)
                                ) {

                                    TextField(
                                        value = namaKelas,
                                        onValueChange = { namaKelas = it },
                                        label = { Text("Nama Kelas") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    //=========================
                                    //TextField untuk semester
                                    //=========================
                                    TextField(
                                        value = semester,
                                        onValueChange = {
                                            // Validasi input hanya angka dan kurang dari atau sama dengan 8
                                            if (it.all { char -> char.isDigit() } && (it.toIntOrNull()
                                                    ?: 0) <= 8) {
                                                semester = it
                                                showWarning =
                                                    false // Hilangkan warning jika valid
                                            } else {
                                                showWarning =
                                                    true // Tampilkan warning jika tidak valid
                                            }
                                        },
                                        label = { Text("Semester") },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number // Keyboard numerik
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    if (showWarning) {
                                        Text(
                                            text = "Input tidak lebih dari 8.",
                                            color = Color.Red,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(
                                                start = 8.dp,
                                                top = 4.dp
                                            )
                                        )
                                    }

                                    TextField(
                                        value = namaDosen,
                                        onValueChange = { namaDosen = it },
                                        label = { Text("Nama Dosen") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    TextField(
                                        value = kodeMataKuliah,
                                        onValueChange = { kodeMataKuliah = it },
                                        label = { Text("Kode Mata Kuliah") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    //=====================================
                                    //TextField dan pemeriksa untuk input angka pada box sks
                                    //=====================================
                                    TextField(
                                        value = jumlahSKS,
                                        onValueChange = {
                                            // Validasi input hanya angka dan kurang dari atau sama dengan 8
                                            if (it.all { char -> char.isDigit() } && (it.toIntOrNull()
                                                    ?: 0) <= 3) {
                                                jumlahSKS = it
                                                showWarningSKS =
                                                    false // Hilangkan warning jika valid
                                            } else {
                                                showWarningSKS =
                                                    true // Tampilkan warning jika tidak valid
                                            }
                                        },
                                        label = { Text("Jumlah SKS") },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number // Keyboard numerik
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    if (showWarningSKS) {
                                        Text(
                                            text = "Maksimal Input adalah 3 SKS.",
                                            color = Color.Red,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(
                                                start = 8.dp,
                                                top = 4.dp
                                            )
                                        )
                                    }

                                    TextField(
                                        value = hariPerkuliahan,
                                        onValueChange = { hariPerkuliahan = it },
                                        label = { Text("Hari Perkuliahan") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    TextField(
                                        value = jamPerkuliahan,
                                        onValueChange = { jamPerkuliahan = it },
                                        label = { Text("Jam Perkuliahan") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    TextField(
                                        value = gedungPerkuliahan,
                                        onValueChange = { gedungPerkuliahan = it },
                                        label = { Text("Gedung Perkuliahan") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    TextField(
                                        value = ruanganPerkuliahan,
                                        onValueChange = { ruanganPerkuliahan = it },
                                        label = { Text("Ruang Perkuliahan") },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            colorResource(R.color.black),
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                }
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                        //============================
                        // Tombol Batal dan Buat Kelas
                        //============================
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //=====================
                            //Tombol Batal
                            //=====================
                            TextButton(
                                onClick = {
                                    // Reset form jika tombol Batal diklik
                                    namaKelas = ""
                                    semester = ""
                                    namaDosen = ""
                                    kodeMataKuliah = ""
                                    jumlahSKS = ""
                                    hariPerkuliahan = ""
                                    jamPerkuliahan = ""
                                    gedungPerkuliahan = ""
                                    ruanganPerkuliahan = ""
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .weight(1f)
                                    .padding(vertical = 5.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                                ) {
                                    Text(
                                        text = "Batal",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            //=====================
                            //Tombol Batal
                            //=====================
                            TextButton(
                                onClick = {
                                    //Pengecekan apakah seluruh field telah diisi?
                                    if(namaKelas.isNotEmpty()&& semester.isNotEmpty()&&namaDosen.isNotEmpty()&&kodeMataKuliah.isNotEmpty()&&jumlahSKS.isNotEmpty()&&hariPerkuliahan.isNotEmpty()&&jamPerkuliahan.isNotEmpty()&&gedungPerkuliahan.isNotEmpty()&&ruanganPerkuliahan.isNotEmpty()){
                                        showDialog = true
                                    } else {
                                        showEmptyFieldAlert = true
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .weight(1f)
                                    .padding(vertical = 5.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_light_blue)),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                                ) {
                                    Text(
                                        text = "Buat Kelas",
                                        color = colorResource(R.color.very_dark_blue),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                            // Dialog peringatan jika field kosong
                            if (showEmptyFieldAlert) {
                                AlertDialog(
                                    onDismissRequest = { showEmptyFieldAlert = false},
                                    title = { Text("Peringatan") },
                                    text = { Text("Harap isi semua field sebelum membuat kelas.") },
                                    confirmButton = {
                                        Button(
                                            onClick = { showEmptyFieldAlert = false }
                                        ) {
                                            Text("OK")
                                        }
                                    }
                                )
                            }
                            // Dialog konfirmasi
                            if (showDialog) {
                                AlertDialog(
                                    onDismissRequest = { showDialog = false },
                                    title = { Text("Konfirmasi") },
                                    text = {
                                        Column {
                                            Text("Kelas berhasil dibuat dengan data berikut:")
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text("Nama Kelas: $namaKelas", fontWeight = FontWeight.Bold)
                                            Text("Semester: $semester", fontWeight = FontWeight.Bold)
                                            Text("Nama Dosen: $namaDosen", fontWeight = FontWeight.Bold)
                                            Text("Kode Mata Kuliah: $kodeMataKuliah", fontWeight = FontWeight.Bold)
                                            Text("Jumlah SKS: $jumlahSKS", fontWeight = FontWeight.Bold)
                                            Text("Hari Perkuliahan: $hariPerkuliahan", fontWeight = FontWeight.Bold)
                                            Text("Jam Perkuliahan: $jamPerkuliahan", fontWeight = FontWeight.Bold)
                                            Text("Gedung Perkuliahan: $gedungPerkuliahan", fontWeight = FontWeight.Bold)
                                            Text("Ruang Perkuliahan: $ruanganPerkuliahan", fontWeight = FontWeight.Bold)
                                        }
                                    },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                showDialog = false
                                                namaKelas = ""
                                                semester = ""
                                                namaDosen = ""
                                                kodeMataKuliah = ""
                                                jumlahSKS  = ""
                                                hariPerkuliahan = ""
                                                jamPerkuliahan = ""
                                                gedungPerkuliahan = ""
                                                ruanganPerkuliahan = ""
                                            }

                                        ) {
                                            Text("OK")
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                //==============================
                //Pilihan Jika Kelas Umum
                //==============================
                0 -> {
                    Column() {
                        Card(
                            Modifier.background(
                                colorResource(R.color.very_light_blue),
                                shape = RoundedCornerShape(15.dp)
                            )
                        ) {
                            Column(
                                modifier = Modifier.background(colorResource(R.color.very_light_blue))
                            ) {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(
                                        topStart = 15.dp,
                                        topEnd = 15.dp
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .background(colorResource(R.color.very_dark_blue))
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Detail Kelas",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                                Column(
                                    Modifier.padding(16.dp)
                                ) {
                                    // Nama Kelas
                                    TextField(
                                        value = namaKelasUmum,
                                        onValueChange = { namaKelasUmum = it },
                                        label = { Text("Nama Kelas") },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    // Semester
                                    TextField(
                                        value = semesterUmum,
                                        onValueChange = {
                                            if (it.all { char -> char.isDigit() } && (it.toIntOrNull() ?: 0) <= 8) {
                                                semesterUmum = it
                                                showWarning = false
                                            } else {
                                                showWarning = true
                                            }
                                        },
                                        label = { Text("Semester") },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        colors = TextFieldDefaults.colors(
                                            unfocusedContainerColor = Color.White,
                                            focusedContainerColor = Color.White,
                                            unfocusedLabelColor = Color.Black,
                                            focusedLabelColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        shape = RoundedCornerShape(10.dp)
                                    )

                                    if (showWarning) {
                                        Text(
                                            text = "Input tidak lebih dari 8.",
                                            color = Color.Red,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(
                                                start = 8.dp,
                                                top = 4.dp
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }

                        // Tombol Batal dan Buat Kelas
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Tombol Batal
                            TextButton(
                                onClick = {
                                    namaKelasUmum = ""
                                    semesterUmum = ""
                                    showEmptyFieldAlert = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .weight(1f)
                                    .padding(vertical = 5.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_dark_blue)),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    text = "Batal",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            // Tombol Buat Kelas
                            TextButton(
                                onClick = {
                                    if (namaKelasUmum.isNotEmpty() && semesterUmum.isNotEmpty()) {
                                        showDialog = true
                                    } else {
                                        showEmptyFieldAlert = true
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .weight(1f)
                                    .padding(vertical = 5.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.very_light_blue)),
                                shape = RoundedCornerShape(15.dp)
                            ) {
                                Text(
                                    text = "Buat Kelas",
                                    color = colorResource(R.color.very_dark_blue),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Dialog peringatan jika field kosong
                        if (showEmptyFieldAlert) {
                            AlertDialog(
                                onDismissRequest = { showEmptyFieldAlert = false },
                                title = { Text("Peringatan") },
                                text = { Text("Harap isi semua field sebelum membuat kelas.") },
                                confirmButton = {
                                    Button(
                                        onClick = { showEmptyFieldAlert = false }
                                    ) {
                                        Text("OK")
                                    }
                                }
                            )
                        }

                        // Dialog konfirmasi jika kelas berhasil dibuat
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("Konfirmasi") },
                                text = {
                                    Column {
                                        Text("Kelas berhasil dibuat dengan data berikut:")
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text("Nama Kelas: $namaKelasUmum", fontWeight = FontWeight.Bold)
                                        Text("Semester: $semesterUmum", fontWeight = FontWeight.Bold)
                                    }
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            showDialog = false
                                            namaKelasUmum = ""
                                            semesterUmum = ""
                                        }
                                    ) {
                                        Text("OK")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}