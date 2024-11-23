package com.example.proyekakhirpemrogramanmobile.model

enum class ScheduleStatus {
    PRESENT,    // perkuliahan diadakan
    UNKNOWN,    // tidak ada informasi
    CANCELLED   // perkuliahan dibatalkan
}

data class ScheduleModel(
    val status: ScheduleStatus,
    val subject: String,
    val time: String,
    val location: String,
    val notes: String
)