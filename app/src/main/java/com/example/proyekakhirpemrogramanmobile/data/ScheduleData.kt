package com.example.proyekakhirpemrogramanmobile.data

import com.example.proyekakhirpemrogramanmobile.model.ScheduleModel
import com.example.proyekakhirpemrogramanmobile.model.ScheduleStatus

val listSchedule = listOf(
    ScheduleModel(
        status = ScheduleStatus.PRESENT,
        subject = "Pemrograman Mobile",
        time = "08.00 – 10.30",
        location = "Ruangan 104",
        notes = "Hari ini akan diadakan presentasi proyek akhir setiap kelompok"
    ),
    ScheduleModel(
        status = ScheduleStatus.UNKNOWN,
        subject = "Metodologi Penelitian",
        time = "10.30 – 12.10",
        location = "Ruangan 101",
        notes = "Tidak ada informasi. Tetapi tetap masuk sesuai dengan jadwal"
    ),
    ScheduleModel(
        status = ScheduleStatus.CANCELLED,
        subject = "Grafika Komputer",
        time = "13.00 – 14.40",
        location = "Ruangan 106",
        notes = "Perkuliahan dibatalkan karena dosen memiliki halangan"
    )
)

//val listSchedule = emptyList<ScheduleModel>()