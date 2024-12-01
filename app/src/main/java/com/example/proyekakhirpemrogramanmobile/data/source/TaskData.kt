package com.example.proyekakhirpemrogramanmobile.data.source

import com.example.proyekakhirpemrogramanmobile.data.model.TaskModel
import com.example.proyekakhirpemrogramanmobile.data.model.TaskType

val listTask = listOf(
    TaskModel(
        course = "Pemrograman Mobile",
        deadline = "5 jam lagi",
        title = "Pengumpulan proyek akhir",
        type = TaskType.GROUP
    ),
    TaskModel(
        course = "Metodologi Penelitian",
        deadline = "2 hari lagi",
        title = "Matriks penelitian",
        type = TaskType.PERSONAL
    ),
//    TaskModel(
//        subject = "Metodologi Penelitian",
//        deadline = "11 jam yang lalu",
//        title = "Matriks penelitian",
//        type = TaskType.PERSONAL
//    ),
//    TaskModel(
//        subject = "Pemrograman Mobile",
//        deadline = "2 bulan yang lalu",
//        title = "Pengumpulan proyek akhir",
//        type = TaskType.GROUP
//    ),
)

//val listTask = emptyList<TaskModel>()