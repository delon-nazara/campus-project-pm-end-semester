package com.example.proyekakhirpemrogramanmobile.data

import com.example.proyekakhirpemrogramanmobile.model.AnnouncementModel

val listAnnouncement = listOf(
    AnnouncementModel(
        course = "Cloud Computing",
        date = "22/10/2024",
        title = "Jadwal Kelas Ganti Cloud Computing",
        description = "Jadwal kelas ganti pertemuan ke-11 mata kuliah Cloud Computing adalah hari Sabtu, 12 Desember 2025. Perkuliahan dilakukan secara daring, dan link zoom meeting akan dibagikan pada hari tersebut."
    ),
    AnnouncementModel(
        course = "Pemrograman Mobile",
        date = "08/10/2024",
        title = "Ujian Akhir Semester",
        description = "Ujian Akhir Semester (UAS) mata kuliah Pemrograman Mobile dilaksanakan pada hari Rabu, 22 Desember 2025. UAS tidak dilaksanakan dalam bentuk ujian tertulis, namun dalam bentuk presentasi proyek akhir."
    )
)

//val listAnnouncement = emptyList<AnnouncementModel>()