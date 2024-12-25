package com.example.proyekakhirpemrogramanmobile.data.model

data class Vote(
    val name : String,
    val count : Int
)

data class VoteList(
    val id : String,
    val title : String,
    val options : List<Vote>
)
