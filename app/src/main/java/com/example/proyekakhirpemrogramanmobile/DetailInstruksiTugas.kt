package com.example.proyekakhirpemrogramanmobile

data class DetailInstruksiTugas(
    var namaMatkul : String,
    var tanggalDitugaskan : String,
    var tanggalDikumpulkan : String,
    var perintahTugas : String,
    var linkTerkait : String,
    var iconJenisTugas : Int
)

fun getDetailInstruksiTugas(): List<DetailInstruksiTugas>{
    return listOf<DetailInstruksiTugas>(
        DetailInstruksiTugas(
            "Grafika Komputer",
            "Rabu, 20 November 2024",
            "Rabu, 27 November 2024",
            "Buatlah objek 3 dimensi menggunakan library freeglut",
            "https://youtu.be/NH9yuZUrJVc?si=AvlSa91HOEnXXbOK",
            R.drawable.tugas_pribadi
        ),
    )
}
