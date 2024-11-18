package com.example.proyekakhirpemrogramanmobile

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHome() {
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
                            .background(colorResource(R.color.dark_blue))
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
                        titleContentColor = colorResource(id = R.color.birulangit)
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
            IsiHome(contentPadding)
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IsiHome(paddingValues: PaddingValues) {

    var currentDateTime by remember { mutableStateOf(LocalDateTime.now()) }

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
            .padding(paddingValues) // Properly apply padding here
            .background(Color.White)
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )
        //===================================
        // Tanggal serta jam saat ini
        //===================================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = colorResource(R.color.dark_blue),
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

        Spacer(
            modifier = Modifier.padding(5.dp)
        )

        //===================================
        // Kalender
        //===================================
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.birulangit)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.dark_blue))
                ) {
                    Text(
                        text = "Kalender",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                }
                Row{
                    Text("hello world")
                }
            }
        }

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        //===================================
        // Jadwal hari ini
        //===================================
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.birulangit)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.dark_blue))
                ) {
                    Text(
                        text = "Jadwal hari ini",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row{
                    Text("hello world")
                }
            }
        }

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        //===================================
        // Tugas hari ini
        //===================================
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.birulangit)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.dark_blue))
                ) {
                    Text(
                        text = "Tugas hari ini",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row{
                    Text("hello world")
                }
            }
        }

        Spacer(
            modifier = Modifier.padding(10.dp)
        )

        //===================================
        // Tugas besok
        //===================================
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.birulangit)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.dark_blue))
                ) {
                    Text(
                        text = "Tugas besok",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row{
                    Text("hello world")
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun CustomCalendar() {
//    val currentDate = remember { LocalDate.now() }
//
//    // Set tanggal yang ingin diwarnai khusus
//    val specialDates = listOf(
//        currentDate.withDayOfMonth(6),  // Contoh tanggal khusus
//        currentDate.withDayOfMonth(12),
//        currentDate.withDayOfMonth(21)
//    )
//
//    val daysOfWeek = listOf("S", "S", "R", "K", "J", "S", "M")
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .background(Color.White)
//    ) {
//        // Header hari dalam seminggu
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            daysOfWeek.forEach { day ->
//                Text(text = day, color = Color.White, fontSize = 16.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Layout kalender bulan ini
//        for (week in 1..5) { // Misalnya 5 minggu dalam 1 bulan
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//                for (day in 1..7) {
//                    val date = currentDate.withDayOfMonth((week - 1) * 7 + day)
//                    val isSpecialDate = specialDates.contains(date)
//                    val isToday = date == currentDate
//
//                    Box(
//                        modifier = Modifier
//                            .size(40.dp)
//                            .background(
//                                color = when {
//                                    isToday -> Color.Cyan  // Warna lingkaran untuk hari ini
//                                    isSpecialDate -> Color.Red.copy(alpha = 0.3f) // Warna tanggal khusus
//                                    else -> Color.Transparent
//                                },
//                                shape = CircleShape
//                            ),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = date.dayOfMonth.toString(),
//                            color = if (isToday) Color.White else Color.Black,
//                            style = if (isSpecialDate) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall
//                        )
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMainHome() {
    MainHome()
}