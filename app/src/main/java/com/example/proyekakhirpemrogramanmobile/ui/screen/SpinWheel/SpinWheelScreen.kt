package com.example.proyekakhirpemrogramanmobile.ui.screen.SpinWheel

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.spin_wheel_compose.SpinWheel
import com.commandiron.spin_wheel_compose.SpinWheelDefaults
import com.commandiron.spin_wheel_compose.state.rememberSpinWheelState
import kotlinx.coroutines.launch
import kotlin.random.Random

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SpinWheelTool(
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(true) }
    val candidates = remember { mutableStateListOf("Slot1", "Slot2", "Slot3") }

    if (showDialog) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Edit the Spin Wheel") },
                text = {
                    Column (
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ){
                        candidates.forEachIndexed { index, candidate ->
                            TextField(
                                value = candidate,
                                onValueChange = { candidates[index] = it },
                                label = { Text("Candidate ${index + 1}") },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            candidates.removeAt(index)
                                        },
                                        enabled = candidates.size > 1
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Remove")
                                    }
                                }
                            )
                        }
                        Button(
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = { candidates.add("New Slot") }
                        ) {
                            Text("Add Slot")
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    } else {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val spinWheelState = rememberSpinWheelState(
                pieCount = candidates.size,
                easing = LinearOutSlowInEasing
            )
            val scope = rememberCoroutineScope()

            var resultDialog by remember { mutableStateOf(false) }
            var resultIndex by remember { mutableIntStateOf(-1) }

            SpinWheel(
                state = spinWheelState,
                dimensions = SpinWheelDefaults.spinWheelDimensions(
                    spinWheelSize = 380.dp,
                    frameWidth = 15.dp,
                    selectorWidth = 20.dp,
                ),
                colors = SpinWheelDefaults.spinWheelColors(
                    frameColor = Color(13, 21, 122),
                    pieColors = List(candidates.size) {
                        Color(
                            Random.nextInt()
                        )
                    }
                ),
                onClick = { scope.launch { spinWheelState.animate { index ->
                    resultIndex = index
                    resultDialog = true
                } } }
            ) { pieIndex ->
                Text(
                    text = candidates[pieIndex],
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = { showDialog = true }
            ) {
                Text("Edit")
            }

            if (resultDialog) {
                AlertDialog(
                    onDismissRequest = {
                        resultDialog = false
                        scope.launch { spinWheelState.reset() }
                    },
                    title = { Text("Spin Result :") },
                    text = { Text(
                        text = candidates[resultIndex],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )},
                    confirmButton = {
                        Button(
                            onClick = {
                                resultDialog = false
                                scope.launch { spinWheelState.reset() }
                            }) {
                            Text("Close")
                        }
                    }
                )
            }
        }
    }
}