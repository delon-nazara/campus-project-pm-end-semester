package com.example.proyekakhirpemrogramanmobile.view

import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.utils.Poppins
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        onStartButtonClicked = {}
    )
}

@Composable
fun OnboardingScreen(
    onStartButtonClicked: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {4})
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingPage(
                    image = R.drawable.onboarding_screen_image_1,
                    title = R.string.onboarding_screen_title_page_1,
                    description = R.string.onboarding_screen_description_page_1
                )
                1 -> OnboardingPage(
                    image = R.drawable.onboarding_screen_image_2,
                    title = R.string.onboarding_screen_title_page_2,
                    description = R.string.onboarding_screen_description_page_2
                )
                2 -> OnboardingPage(
                    image = R.drawable.onboarding_screen_image_3,
                    title = R.string.onboarding_screen_title_page_3,
                    description = R.string.onboarding_screen_description_page_3
                )
                3 -> OnboardingPage(
                    image = R.drawable.onboarding_screen_image_4,
                    title = R.string.onboarding_screen_title_page_4,
                    description = R.string.onboarding_screen_description_page_4
                )
            }
        }

        // Navigation
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.white))
                .padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            var leftText by rememberSaveable { mutableStateOf("") }
            var rightText by rememberSaveable { mutableStateOf("") }
            var enabledButton by rememberSaveable { mutableStateOf(true) }

            when (pagerState.currentPage) {
                0 -> {
                    leftText = ""
                    rightText = "Next"
                    enabledButton = false
                }
                3 -> {
                    leftText = "Back"
                    rightText = "Start"
                    enabledButton = true
                }
                else -> {
                    rightText = "Next"
                    leftText = "Back"
                    enabledButton = true
                }
            }

            // Back Button
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage - 1,
                            animationSpec = tween(500)
                        )
                    }
                },
                enabled = enabledButton,
                modifier = Modifier.width(75.dp)
            ) {
                Text(
                    text = leftText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.very_light_blue)
                )
            }

            // Page Indicator
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                for (i in 0 until pagerState.pageCount) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                if (i == pagerState.currentPage) {
                                    colorResource(R.color.dark_gray)
                                } else {
                                    colorResource(R.color.light_gray)
                                },
                                shape = CircleShape
                            )
                    )
                    if (i != 3) {
                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )
                    }
                }
            }

            // Next Button
            TextButton(
                onClick = {
                    if (pagerState.currentPage == 3) {
                        onStartButtonClicked()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(500)
                            )
                        }
                    }
                },
                modifier = Modifier.width(75.dp)
            ) {
                Text(
                    text = rightText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.very_light_blue)
                )
            }

        }
    }
}

@Composable
fun OnboardingPage(
    image: Int,
    title: Int,
    description: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.light_blue),
                        colorResource(R.color.white)
                    ),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ) {
        // Image
        Image(
            painter = painterResource(image),
            contentDescription = "Onboarding image",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Line
        HorizontalDivider(
            thickness = 3.dp,
            color = colorResource(R.color.gray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Title
        Text(
            text = stringResource(title),
            fontSize = 30.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Description
        Text(
            text = stringResource(description),
            fontSize = 18.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}