package com.example.proyekakhirpemrogramanmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekakhirpemrogramanmobile.ui.screen.CreateVote.CreateVoteScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.Voting.VotingScreen
import com.example.proyekakhirpemrogramanmobile.ui.screen.VotingList.VotingListViewModel
import com.example.proyekakhirpemrogramanmobile.ui.theme.ProyekAkhirPemrogramanMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyekAkhirPemrogramanMobileTheme {
                Testing()
            }
        }
    }
}

@Composable
fun Testing(viewModel: VotingListViewModel = viewModel()) {
    CreateVoteScreen()
}
