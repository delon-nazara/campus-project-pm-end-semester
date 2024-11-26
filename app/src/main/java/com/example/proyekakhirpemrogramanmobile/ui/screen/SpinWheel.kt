package com.example.proyekakhirpemrogramanmobile.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.spin_wheel_compose.SpinWheel
import com.commandiron.spin_wheel_compose.SpinWheelDefaults
import com.commandiron.spin_wheel_compose.state.rememberSpinWheelState
import kotlinx.coroutines.launch

@Preview(
    showBackground = true
)
@Composable
fun TestWheel() {
    val textList by remember {
        mutableStateOf(
            listOf("Pie 1", "Pie 2", "Pie 3", "Pie 4", "Pie 5", "Pie 6", "Pie 7", "Pie 8")
        )
    }

    val state = rememberSpinWheelState()
    val scope = rememberCoroutineScope()

    SpinWheel(
        state = state,
        dimensions = SpinWheelDefaults.spinWheelDimensions(
            spinWheelSize = 200.dp,
            frameWidth = 10.dp,
            selectorWidth = 10.dp
        ),
        onClick = { scope.launch { state.animate {_ -> } } }
    ){ pieIndex ->
        Text(text = textList[pieIndex])
    }
}

