package com.example.proyekakhirpemrogramanmobile

data class jadwalHariIni(
    var namaMatakuliah : String,
    var jamKelas : String,
    var lokasiKelas : String,
    var catatanDosen : String,
//    var statusKelas : Boolean
)

fun getjadwalHariIni(): List<jadwalHariIni> {
    return listOf<jadwalHariIni>(
       jadwalHariIni("Pemrograman Mobile", "10.30-13.00", "Gedung D, 104","Makanlah yang banyak nak"),
        jadwalHariIni("Cloud Computing", "14.40-17.10", "Gedung D, 106", "Jangan Lupa Makan dan bersyukur"),
        jadwalHariIni("LAB Grafika Komputer","17.10=18.00","Gedung D, LAB Pemrograman 4", "Rawrr!"),
    )
}

data class tugasHariIni(
    var namaMatakuliah: String,
    var jenisTugas : Int,
    var deadlineTugas : String,
    var perintahTugas : String,
)

fun getTugasHariIni(): List<tugasHariIni>{
    return listOf<tugasHariIni>(
        tugasHariIni("Pemrograman Mobile", R.drawable.tugas_kelompok,"Kamis, 28 November 2024, 23.59","Buatlah aplikasi yang dapat menggeser satelit Voyager II"),
        tugasHariIni("Grafika Komputer", R.drawable.tugas_pribadi,"Senin, 26 November 2024, 23.59","Buatlah PPT mengenain konsep morphing")
    )
}

data class tugasBesok(
    var namaMatakuliah: String,
    var jenisTugas : Int,
    var deadlineTugas : String,
    var perintahTugas : String,
)

fun getTugasBesok(): List<tugasBesok>{
    return listOf<tugasBesok>(
        tugasBesok("Kripto", R.drawable.tugas_kelompok,"Kamis, 28 November 2024, 23.59","Buatlah aplikasi yang dapat menggeser satelit Voyager II"),
        tugasBesok("Analisis", R.drawable.tugas_pribadi,"Senin, 26 November 2024, 23.59","Buatlah PPT mengenain konsep morphing")
    )
}

