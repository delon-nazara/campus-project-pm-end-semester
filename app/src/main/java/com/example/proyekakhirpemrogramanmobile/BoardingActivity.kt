package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider

class BoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainBoarding()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainBoarding() {
    val pagerState = rememberPagerState (pageCount = {4})
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //==================================================
        // Horizontal Pager untuk navigasi antar halaman
        //==================================================
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> PageBoarding1()
                1 -> PageBoarding2()
                2 -> PageBoarding3()
                3 -> PageBoarding4()
            }
        }
        //==================================================
        // Bottom navigasi bar
        //==================================================
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 16.dp)
        ) {
            if (pagerState.currentPage > 0) {
                TextButton(
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    },
                    modifier = Modifier.background(Color.Transparent).padding(16.dp)
                ) {
                    Text(
                        text = "Back",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.very_light_blue)
                    )
                }
            }
            else {
                Spacer(modifier = Modifier.padding(horizontal = 53.dp))
            }

            PageIndicator(currentPage = pagerState.currentPage, pageCount = pagerState.pageCount)

            if (pagerState.currentPage < pagerState.pageCount - 1) {
                TextButton(
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    },
                    modifier = Modifier.background(Color.Transparent).padding(16.dp)
                ) {
                    Text(
                        text = "Next",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.very_light_blue)
                    )
                }
            } else {
                //================================================================================================
                // Button start (button terakhir untuk onboarding page yang akan mengarahkan ke halaman homescreen
                //================================================================================================
                TextButton(
                    onClick = {
                        // Logika ketika onboarding selesai
                    },
                    modifier = Modifier.background(Color.Transparent).padding(16.dp)
                ) {
                    Text(
                        text = "Start",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.very_light_blue)
                    )
                }
            }
        }
    }
}
//==================================================
// Layout page 1
//==================================================
@Composable
fun PageBoarding1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.birulangit),
                        Color.White
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding1),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp),
                thickness = 3.dp,
                color = Color.Gray
            )

            Text(
                text = "Selamat datang!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Text(
                text = "Mari lihat semua yang kamu butuhkan dalam satu aplikasi",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//==================================================
// Layout page 2
//==================================================
@Composable
fun PageBoarding2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.birulangit),
                        Color.White
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding2),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp),
                thickness = 3.dp,
                color = Color.Gray
            )

            Text(
                text = "Jadwal Kelas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Text(
                text = "Tetap terinformasi dengan perubahan jadwal yang mungkin terjadi",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//==================================================
// Layout page 3
//==================================================
@Composable
fun PageBoarding3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.birulangit),
                        Color.White
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding3),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp),
                thickness = 3.dp,
                color = Color.Gray
            )

            Text(
                text = "Informasi Tugas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Text(
                text = "Asisten pribadi kamu untuk melihat tugas dan tanggal yang terbaru",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//==================================================
// Layout page 4
//==================================================
@Composable
fun PageBoarding4() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.birulangit),
                        Color.White
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding4),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp, horizontal = 20.dp),
                thickness = 3.dp,
                color = Color.Gray
            )

            Text(
                text = "Informasi Modul",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 5.dp)
            )

            Text(
                text = "Pelajari kembali materi yang sudah dipelajari, kapanpun dan dimanapun",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//==================================================
// Page Indicator
//==================================================
@Composable
fun PageIndicator(currentPage: Int, pageCount: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
    ) {
        for (i in 0 until pageCount) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        if (i == currentPage) Color.DarkGray else Color.LightGray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    MainBoarding()
}
