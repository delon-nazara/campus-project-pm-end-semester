package com.example.proyekakhirpemrogramanmobile.archive

import com.example.proyekakhirpemrogramanmobile.R

data class DetailMataKuliah(
    var namaMataKuliah : String,
    var namaKetuaKelas : String,
    var jumlahMahasiswa : String,
    var semesterMataKuliah : Int,
    var imageRes : Int,
)

fun getDetailMataKuliah(): List<DetailMataKuliah>{
    return listOf<DetailMataKuliah>(
            DetailMataKuliah("Pemrograman Mobile", "Steven Anthony", "35",5,
                R.drawable.laptop_icon
            ),
            DetailMataKuliah("Grafika Komputer", "Iqbal Alwi", "29",5, R.drawable.laptop_icon),
            DetailMataKuliah("Algoritma Pemrograman", "Delon Nazara", "78",5,
                R.drawable.laptop_icon
            ),
    )
}