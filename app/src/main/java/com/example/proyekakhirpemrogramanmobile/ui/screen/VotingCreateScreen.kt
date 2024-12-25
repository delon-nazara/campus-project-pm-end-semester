package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekakhirpemrogramanmobile.data.model.Vote
import com.example.proyekakhirpemrogramanmobile.viewmodel.VotingCreateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VotingCreateScreen() {
    val viewModel: VotingCreateViewModel = viewModel()
    val isSubmitting = viewModel.isSubmitting.collectAsState()

    var title by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(mutableListOf<Vote>()) }
    var optionName by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            confirmButton = {
                TextButton(onClick = { showSuccessDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Success") },
            text = { Text("Vote created successfully!") }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create New Vote") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Vote Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = optionName,
                    onValueChange = { optionName = it },
                    label = { Text("Option Name") },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (optionName.isNotBlank()) {
                            options = options.toMutableList().apply {
                                add(Vote(name = optionName, count = 0))
                            }
                            optionName = ""
                        }
                    }
                ) {
                    Text("Add Option")
                }

            }

            Text("Options:", style = MaterialTheme.typography.titleMedium)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(options) { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = option.name, style = MaterialTheme.typography.bodyMedium)
                        IconButton(onClick = {
                            options = options.toMutableList().apply {
                                remove(option)
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Remove Option")
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (title.isNotBlank() && options.isNotEmpty()) {
                        viewModel.createVote(
                            title = title,
                            options = options,
                            onSuccess = { showSuccessDialog = true },
                            onError = { exception -> exception.printStackTrace() }
                        )
                    }
                },
                enabled = !isSubmitting.value && title.isNotBlank() && options.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isSubmitting.value) "Submitting..." else "Save")
            }
        }
    }
}