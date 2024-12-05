package com.example.proyekakhirpemrogramanmobile.ui.screen.Voting

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.domain.Voting.Vote
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VotingViewModel : ViewModel() {
    private var db = Firebase.firestore

    private var _votes = MutableStateFlow<List<Vote>>(emptyList())
    var votes = _votes.asStateFlow()

    private fun fetchVote() {
        db.collection("")
    }
}