package com.example.proyekakhirpemrogramanmobile.ui.screen.VotingList

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.domain.Voting.Vote
import com.example.proyekakhirpemrogramanmobile.domain.Voting.VoteList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VotingListViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _voteList = MutableStateFlow<List<VoteList>>(emptyList())
    val voteList = _voteList.asStateFlow()

    init {
        fetchVoteLists()
    }

    private fun fetchVoteLists() {
        db.collection("Votes")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    exception.printStackTrace()
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val events = snapshot.documents.map { doc ->
                        val title = doc.getString("Title") ?: "Untitled Voting"
                        val options = (doc.get("Options") as? List<Map<String, Any>>)?.map {
                            Vote(
                                name = it["name"] as String,
                                count = (it["count"] as Long).toInt()
                            )
                        } ?: emptyList()
                        VoteList(doc.id, title, options)
                    }
                    _voteList.value = events
                }
            }
    }
}