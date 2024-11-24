package com.example.proyekakhirpemrogramanmobile.archive

import com.example.proyekakhirpemrogramanmobile.R

data class DetailMatkul(
    var modulName : String,
    var onSemester : String,
    var jumlahModul : String,
    var imageRes : Int,
)

fun getModulDetail() : List<DetailMatkul>{
     return listOf<DetailMatkul>(
         DetailMatkul("Grafika Komputer", "5","9", R.drawable.laptop_icon),
         DetailMatkul("Pemrograman Mobile", "5", "6", R.drawable.laptop_icon),
         DetailMatkul("Struktur Data", "5","10", R.drawable.laptop_icon),
         DetailMatkul("Pemrograman Web", "3","50", R.drawable.laptop_icon),
     )
}
