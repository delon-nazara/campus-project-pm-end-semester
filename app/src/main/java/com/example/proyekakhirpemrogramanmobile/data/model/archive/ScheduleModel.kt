package com.example.proyekakhirpemrogramanmobile.data.model.archive

enum class ScheduleStatus {
    PRESENT,    // perkuliahan diadakan
    UNKNOWN,    // tidak ada informasi
    CANCELLED   // perkuliahan dibatalkan
}

data class ScheduleModel(
    val status: ScheduleStatus,
    val course: String,
    val time: String,
    val location: String,
    val notes: String
)