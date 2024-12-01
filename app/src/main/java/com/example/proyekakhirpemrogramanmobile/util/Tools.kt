package com.example.proyekakhirpemrogramanmobile.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Locale

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun formatName(name: String): String {
    return name
        .lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { firstLetter ->
                firstLetter.uppercase()
            }
        }
}

fun getFirstWord(name: String): String {
    return name.split(" ").first()
}

fun getFirstChar(name: String): Char {
    return name[0].lowercaseChar()
}

fun getCurrentMilliseconds(): Long {
    return System.currentTimeMillis()
}

fun formatDate(milliseconds: Long): String {
    val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatDateWithoutDay(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatTime(milliseconds: Long): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(milliseconds)
}