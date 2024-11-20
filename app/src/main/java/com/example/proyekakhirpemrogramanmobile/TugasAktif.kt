package com.example.proyekakhirpemrogramanmobile

data class TugasAktif(
    var namaMatakuliah : String,
    var deadlineDate : String,
    var iconJenisTugas : Int,
)

data class TugasLampau(
    var namaMatakuliah : String,
    var deadlineDate : String,
    var iconJenisTugas : Int,
)

fun getTugasAktif(): List<TugasAktif> {
    return listOf<TugasAktif>(
        TugasAktif("Kriptografi", "Rabu, 24 Januari 2025", R.drawable.tugas_pribadi),
        TugasAktif("Cloud Computing", "Rabu, 9 Oktober 2024", R.drawable.tugas_kelompok),
        TugasAktif("Grafika Komputer", "Kamis, 12 Desember 2024", R.drawable.tugas_pribadi),
    )
}

fun getTugasLampau(): List<TugasLampau> {
    return listOf<TugasLampau>(
       TugasLampau("Pemrograman Mobile", "Rabu, 24 Januari 2025", R.drawable.tugas_pribadi),
        TugasLampau("Cloud Computing", "Rabu, 9 Oktober 2024", R.drawable.tugas_kelompok),
    )
}