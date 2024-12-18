package com.example.proyekakhirpemrogramanmobile.util

import android.content.Context
import android.widget.Toast
import com.example.proyekakhirpemrogramanmobile.R
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

fun getFirstLetter(name: String): Char {
    return name[0].lowercaseChar()
}

fun setImageBasedLetter(letter: String): Int {
    return when (letter.lowercase()) {
//        "a" -> R.drawable.alphabet_profile_picture_a
//        "b" -> R.drawable.alphabet_profile_picture_b
//        "c" -> R.drawable.alphabet_profile_picture_c
        "d" -> R.drawable.alphabet_profile_picture_d
//        "e" -> R.drawable.alphabet_profile_picture_e
//        "f" -> R.drawable.alphabet_profile_picture_f
//        "g" -> R.drawable.alphabet_profile_picture_g
//        "h" -> R.drawable.alphabet_profile_picture_h
//        "i" -> R.drawable.alphabet_profile_picture_i
//        "j" -> R.drawable.alphabet_profile_picture_j
//        "k" -> R.drawable.alphabet_profile_picture_k
//        "l" -> R.drawable.alphabet_profile_picture_l
//        "m" -> R.drawable.alphabet_profile_picture_m
//        "n" -> R.drawable.alphabet_profile_picture_n
//        "o" -> R.drawable.alphabet_profile_picture_o
//        "p" -> R.drawable.alphabet_profile_picture_p
//        "q" -> R.drawable.alphabet_profile_picture_q
//        "r" -> R.drawable.alphabet_profile_picture_r
//        "s" -> R.drawable.alphabet_profile_picture_s
//        "t" -> R.drawable.alphabet_profile_picture_t
//        "u" -> R.drawable.alphabet_profile_picture_u
//        "v" -> R.drawable.alphabet_profile_picture_v
//        "w" -> R.drawable.alphabet_profile_picture_w
//        "x" -> R.drawable.alphabet_profile_picture_x
//        "y" -> R.drawable.alphabet_profile_picture_y
//        "z" -> R.drawable.alphabet_profile_picture_z
        else -> R.drawable.person_icon
    }
}

fun getCurrentMilliseconds(): Long {
    return System.currentTimeMillis()
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
    val formatter = SimpleDateFormat("HH.mm", Locale.getDefault())
    return formatter.format(milliseconds)
}

fun formatDisplayTime(milliseconds: Long): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(milliseconds)
}