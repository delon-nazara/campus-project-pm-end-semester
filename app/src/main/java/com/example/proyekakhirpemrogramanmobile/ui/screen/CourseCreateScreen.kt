package com.example.proyekakhirpemrogramanmobile.ui.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyekakhirpemrogramanmobile.R
import com.example.proyekakhirpemrogramanmobile.util.formatDate
import com.example.proyekakhirpemrogramanmobile.util.formatTime
import com.example.proyekakhirpemrogramanmobile.util.showToast
import java.util.Calendar

@Preview
@Composable
fun CourseCreateScreen(
    onCancelButtonClicked: () -> Unit = {},
    onConfirmButtonClicked: (List<String>) -> Unit = {},
) {
    val context = LocalContext.current

    var name by rememberSaveable { mutableStateOf("") }
    var credits by rememberSaveable { mutableStateOf("") }
    var lecturer by rememberSaveable { mutableStateOf("") }
    var semester by rememberSaveable { mutableStateOf("") }
    var year by rememberSaveable { mutableStateOf("") }
    var major by rememberSaveable { mutableStateOf("") }
    var faculty by rememberSaveable { mutableStateOf("") }
    var building by rememberSaveable { mutableStateOf("") }
    var floor by rememberSaveable { mutableStateOf("") }
    var room by rememberSaveable { mutableStateOf("") }

    val startDateCalendar = remember { Calendar.getInstance() }
    var startDate by remember { mutableStateOf(formatDate(startDateCalendar.timeInMillis)) }
    val startDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, selectedYear, selectedMonth, selectedDay ->
            startDateCalendar.set(selectedYear, selectedMonth, selectedDay)
            startDate = formatDate(startDateCalendar.timeInMillis)
        },
        startDateCalendar.get(Calendar.YEAR),
        startDateCalendar.get(Calendar.MONTH),
        startDateCalendar.get(Calendar.DAY_OF_MONTH)
    )

    val startTimeCalendar = remember { Calendar.getInstance() }
    var startTime by remember { mutableStateOf(formatTime(startTimeCalendar.timeInMillis)) }
    val startTimePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, selectedHour, selectedMinute ->
            startTimeCalendar.set(Calendar.HOUR_OF_DAY, selectedHour)
            startTimeCalendar.set(Calendar.MINUTE, selectedMinute)
            startTime = formatTime(startTimeCalendar.timeInMillis)
        },
        startTimeCalendar.get(Calendar.HOUR_OF_DAY),
        startTimeCalendar.get(Calendar.MINUTE),
        true
    )

    val endTimeCalendar = remember { Calendar.getInstance() }
    var endTime by remember { mutableStateOf(formatTime(endTimeCalendar.timeInMillis)) }
    val endTimePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, selectedHour, selectedMinute ->
            endTimeCalendar.set(Calendar.HOUR_OF_DAY, selectedHour)
            endTimeCalendar.set(Calendar.MINUTE, selectedMinute)
            endTime = formatTime(endTimeCalendar.timeInMillis)
        },
        endTimeCalendar.get(Calendar.HOUR_OF_DAY),
        endTimeCalendar.get(Calendar.MINUTE),
        true
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Text(
            text = stringResource(R.string.crc_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.very_dark_blue),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.very_dark_blue),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.crc_description),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue),
                        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(vertical = 20.dp, horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                TextInput(
                    value = name,
                    onValueChange = { name = it },
                    label = R.string.crc_input_name
                )
                TextInput(
                    value = credits,
                    onValueChange = { credits = it },
                    label = R.string.crc_input_credits,
                    keyboardType = KeyboardType.Number
                )
                TextInput(
                    value = lecturer,
                    onValueChange = { lecturer = it },
                    label = R.string.crc_input_lecturer
                )
                TextInput(
                    value = semester,
                    onValueChange = { semester = it },
                    label = R.string.crc_input_semester,
                    keyboardType = KeyboardType.Number
                )
                TextInput(
                    value = year,
                    onValueChange = { year = it },
                    label = R.string.crc_input_year,
                    keyboardType = KeyboardType.Number
                )
                TextInput(
                    value = major,
                    onValueChange = { major = it },
                    label = R.string.crc_input_major
                )
                TextInput(
                    value = faculty,
                    onValueChange = { faculty = it },
                    label = R.string.crc_input_faculty
                )
                TextInput(
                    value = building,
                    onValueChange = { building = it },
                    label = R.string.crc_input_building
                )
                TextInput(
                    value = floor,
                    onValueChange = { floor = it },
                    label = R.string.crc_input_floor,
                    keyboardType = KeyboardType.Number
                )
                TextInput(
                    value = room,
                    onValueChange = { room = it },
                    label = R.string.crc_input_room
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.white),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                        .padding(start = 16.dp, end = 6.dp)
                        .padding(vertical = 12.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.crc_input_start_date),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = startDate,
                            fontSize = 14.sp
                        )
                    }
                    IconButton(
                        onClick = { startDatePickerDialog.show() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.next_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.white),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                        .padding(start = 16.dp, end = 6.dp)
                        .padding(vertical = 12.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.crc_input_start_time),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = startTime,
                            fontSize = 14.sp
                        )
                    }
                    IconButton(
                        onClick = { startTimePickerDialog.show() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.next_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(R.color.white),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
                        .padding(start = 16.dp, end = 6.dp)
                        .padding(vertical = 12.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.crc_input_end_time),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = endTime,
                            fontSize = 14.sp
                        )
                    }
                    IconButton(
                        onClick = { endTimePickerDialog.show() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.next_icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onCancelButtonClicked() },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_light_blue)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.crc_cancel),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Button(
                onClick = {
                    val allData = listOf(
                        name,
                        credits,
                        lecturer,
                        semester,
                        year,
                        major,
                        faculty,
                        building,
                        floor,
                        room,
                        startDate,
                        startTime,
                        endTime
                    )

                    if (allData.any { it.isEmpty() }) {
                        showToast(context, "Tidak boleh ada data yang kosong!")
                    } else {
                        onConfirmButtonClicked(allData)
                    }
                },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.very_dark_blue)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.crc_confirm),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: Int,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = {
            Text(
                text = stringResource(label),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.white),
            unfocusedContainerColor = colorResource(R.color.white),
            focusedTextColor = colorResource(R.color.black),
            unfocusedTextColor = colorResource(R.color.black),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, colorResource(R.color.gray), RoundedCornerShape(12.dp))
    )
}