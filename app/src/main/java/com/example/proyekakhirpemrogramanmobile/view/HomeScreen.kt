package com.example.proyekakhirpemrogramanmobile.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.archive.JadwalHariIni
import com.example.proyekakhirpemrogramanmobile.archive.LazyCallerJadwalHariIni
import com.example.proyekakhirpemrogramanmobile.archive.LazyCallerTugasBesok
import com.example.proyekakhirpemrogramanmobile.archive.LazyCallerTugasHariIni
import com.example.proyekakhirpemrogramanmobile.archive.TugasBesok
import com.example.proyekakhirpemrogramanmobile.archive.TugasHariIni
import com.example.proyekakhirpemrogramanmobile.getTugasBesok
import com.example.proyekakhirpemrogramanmobile.getTugasHariIni
import com.example.proyekakhirpemrogramanmobile.getjadwalHariIni
import com.example.proyekakhirpemrogramanmobile.jadwalHariIni
import com.example.proyekakhirpemrogramanmobile.tugasBesok
import com.example.proyekakhirpemrogramanmobile.tugasHariIni
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreen() {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    SideBar(
        coroutineScope = coroutineScope,
        drawerState = drawerState,
        selectedMenu = R.string.sidebar_home
    ) {
        TopBar(
            coroutineScope = coroutineScope,
            drawerState = drawerState
        ) {
            IsiHome()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IsiHome() {

    var currentDateTime by remember { mutableStateOf(LocalDateTime.now()) }
    val scrollState = rememberScrollState()
    // Coroutine to update the time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime = LocalDateTime.now()
            delay(1000L) // Update every 1 second
        }
    }

    // Date and time formatting
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale("in", "ID"))
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
//            .verticalScroll(scrollState)
    ) {
        //===================================
        // Tanggal serta jam saat ini
        //===================================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentDateTime.format(dateFormatter),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
                Text(
                    text = currentDateTime.format(timeFormatter),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ){
            //===================================
            // Kalender Taken by: Delon Nazara
            //===================================
//            Card(
//                colors = CardDefaults.cardColors(
//                    containerColor = colorResource(R.color.birulangit)
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ) {
//                Column {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(colorResource(R.color.dark_blue))
//                    ) {
//                        Text(
//                            text = "Kalender",
//                            modifier = Modifier.padding(16.dp),
//                            color = Color.White,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.SemiBold
//                        )
//
//                    }
//                    Row{
//                        Text("hello world")
//                    }
//                }
//            }
            Spacer(
                modifier = Modifier.padding(5.dp)
            )
            LazyCallerJadwalHariIni()//fungsi untuk membuat card jadwal hari ini
            LazyCallerTugasHariIni()//fungsi untuk membuat card tugas hari ini
            LazyCallerTugasBesok()//fungsi untuk membuat card tugas besok
        }
    }
}

@Composable
fun LazyCallerJadwalHariIni() {
    val mylist = getjadwalHariIni()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(490.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp
            )
        ){
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.very_dark_blue))
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Jadwal Hari Ini",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .background(colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        ){
            items(mylist){ item ->
                JadwalHariIni(item)
            }
        }
    }
}

@Composable
fun JadwalHariIni(item : jadwalHariIni) {
    Card(
        onClick = {
//            logic ke halaman selanjutnya isi ya bang backend awkaowkoa
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)

        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ){
                Row(
                    modifier = Modifier
                        .background(colorResource(R.color.hijau_konfirmasi))
                        .fillMaxWidth()
                ){
                    Text(
                        text = item.namaMatakuliah,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_jam),
                        contentDescription = "icon jam"
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.jamKelas,
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_gedung),
                        contentDescription = "icon jam"
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.lokasiKelas,
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_pencil),
                        contentDescription = "icon jam",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.catatanDosen,
                    )
                }
            }
        }
    }
}

@Composable
fun LazyCallerTugasHariIni() {
    val mylist = getTugasHariIni()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(380.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp
            )
        ){
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.very_dark_blue))
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Tugas Hari Ini",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .background(colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        ){
            items(mylist){ item ->
                TugasHariIni(item)
            }
        }
    }
}

@Composable
fun TugasHariIni(item : tugasHariIni) {
    Card(
        onClick = {
//            logic ke halaman selanjutnya usahakan ya bang hehe
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)

        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = item.namaMatakuliah,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(item.jenisTugas),
                        contentDescription = "icon tugas",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_jam),
                        contentDescription = "icon jam"
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.deadlineTugas,
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_pencil),
                        contentDescription = "icon jam",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.perintahTugas,
                    )
                }
            }
        }
    }
}

@Composable
fun LazyCallerTugasBesok() {
    val mylist = getTugasBesok()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(410.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp
            )
        ){
            Row(
                modifier = Modifier
                    .background(colorResource(R.color.very_dark_blue))
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Tugas Besok",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .background(colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        ){
            items(mylist){ item ->
                TugasBesok(item)
            }
        }
    }
}

@Composable
fun TugasBesok(item : tugasBesok) {
    Card(
        onClick = {
//            logic ke halaman selanjutnya janji ini yang terakhir hjehe
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)

        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = item.namaMatakuliah,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(item.jenisTugas),
                        contentDescription = "icon tugas",
                        modifier = Modifier.size(25.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_jam),
                        contentDescription = "icon jam"
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.deadlineTugas,
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.icon_pencil),
                        contentDescription = "icon jam",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = item.perintahTugas,
                    )
                }
            }
        }
    }
}