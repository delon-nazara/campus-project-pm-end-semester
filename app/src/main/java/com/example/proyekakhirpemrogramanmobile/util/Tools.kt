package com.example.proyekakhirpemrogramanmobile.util

import android.content.Context
import android.widget.Toast
import com.example.proyekakhirpemrogramanmobile.R
import java.text.SimpleDateFormat
import java.util.Locale

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun formatText(input: String): String {
    return input
        .lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { firstLetter ->
                firstLetter.uppercase()
            }
        }
}

fun getFirstWord(input: String): String {
    return input
        .split(" ")
        .first()
}

fun getFirstLetter(input: String): Char {
    return input[0].lowercaseChar()
}

fun getFirstLetters(input: String): String {
    return input
        .split(" ")
        .map { it.firstOrNull()?.lowercaseChar() ?: "" }
        .joinToString("")
}

fun setImageBasedLetter(letter: String): Int {
    return when (letter.lowercase()) {
        "a" -> R.drawable.alphabet_profile_picture_a
        "b" -> R.drawable.alphabet_profile_picture_b
        "c" -> R.drawable.alphabet_profile_picture_c
        "d" -> R.drawable.alphabet_profile_picture_d
        "e" -> R.drawable.alphabet_profile_picture_e
        "f" -> R.drawable.alphabet_profile_picture_f
        "g" -> R.drawable.alphabet_profile_picture_g
        "h" -> R.drawable.alphabet_profile_picture_h
        "i" -> R.drawable.alphabet_profile_picture_i
        "j" -> R.drawable.alphabet_profile_picture_j
        "k" -> R.drawable.alphabet_profile_picture_k
        "l" -> R.drawable.alphabet_profile_picture_l
        "m" -> R.drawable.alphabet_profile_picture_m
        "n" -> R.drawable.alphabet_profile_picture_n
        "o" -> R.drawable.alphabet_profile_picture_o
        "p" -> R.drawable.alphabet_profile_picture_p
        "q" -> R.drawable.alphabet_profile_picture_q
        "r" -> R.drawable.alphabet_profile_picture_r
        "s" -> R.drawable.alphabet_profile_picture_s
        "t" -> R.drawable.alphabet_profile_picture_t
        "u" -> R.drawable.alphabet_profile_picture_u
        "v" -> R.drawable.alphabet_profile_picture_v
        "w" -> R.drawable.alphabet_profile_picture_w
        "x" -> R.drawable.alphabet_profile_picture_x
        "y" -> R.drawable.alphabet_profile_picture_y
        "z" -> R.drawable.alphabet_profile_picture_z
        else -> R.drawable.person_icon
    }
}

fun getCurrentMilliseconds(): Long {
    return System.currentTimeMillis()
}

fun formatDateForId(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatDate(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatDay(milliseconds: Long): String {
    val formatter = SimpleDateFormat("EEEE", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatTime(milliseconds: Long): String {
    val formatter = SimpleDateFormat("HH.mm", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatDisplayTime(milliseconds: Long): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale("id", "ID"))
    return formatter.format(milliseconds)
}

fun formatTimeDifferent(millis: Long): String {
    return when {
        millis < 60_000 -> "${millis / 1_000} detik"
        millis < 3_600_000 -> "${millis / 60_000} menit"
        millis < 86_400_000 -> "${millis / 3_600_000} jam"
        millis < 2_592_000_000 -> "${millis / 86_400_000} hari"
        millis < 31_536_000_000 -> "${millis / 2_592_000_000} bulan"
        else -> "${millis / 31_536_000_000} tahun"
    }
}

fun parseDateAndTime(dateTime: String): Long {
    val formatter = SimpleDateFormat("dd MMMM yyyy HH.mm", Locale("id", "ID"))
    return formatter.parse(dateTime)?.time ?: 0
}