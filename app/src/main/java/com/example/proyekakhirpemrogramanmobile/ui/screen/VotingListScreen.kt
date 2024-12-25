package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekakhirpemrogramanmobile.data.source.Route
import com.example.proyekakhirpemrogramanmobile.viewmodel.VotingListViewModel
import com.example.proyekakhirpemrogramanmobile.viewmodel.VotingViewModel

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VotingListScreen(
    navigateTo: (String, Boolean) -> Unit = { _, _ -> },
    viewModel: VotingListViewModel = viewModel(),
    votingViewModel: VotingViewModel = viewModel()
) {
    val voteList = viewModel.voteList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Voting Events") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateTo(Route.TOOL_VOTING_CREATE_SCREEN.name, false)
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            voteList.value.forEach { event ->
                Card(
                    onClick = {
                        navigateTo(Route.TOOL_VOTING_SCREEN.name, false)
                        votingViewModel.fetchVote(event.id)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Options: ${event.options.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}