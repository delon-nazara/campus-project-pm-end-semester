package com.example.proyekakhirpemrogramanmobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.data.model.Vote
import com.example.proyekakhirpemrogramanmobile.data.model.VoteList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VotingViewModel() : ViewModel() {
    private var db = Firebase.firestore

    private var _votes = MutableStateFlow<VoteList?>(null)
    val votes: StateFlow<VoteList?> = _votes.asStateFlow()

    fun fetchVote(voteId: String) {
        db.collection("vote").document(voteId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    exception.printStackTrace()
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val title = snapshot.getString("Title") ?: "Untitled Voting"
                    val options = (snapshot.get("Options") as? List<Map<String, Any>>)?.map {
                        Vote(
                            name = it["name"] as String,
                            count = (it["count"] as Long).toInt()
                        )
                    } ?: emptyList()

                    _votes.value = VoteList(
                        id = voteId,
                        title = title,
                        options = options
                    )
                }
            }
    }

    fun updateVote(name: String, voteId: String) {
        val currentVote = _votes.value ?: return
        val voteValue = currentVote.options.map { option ->
            if (option.name == name) {
                option.copy(count = option.count + 1)
            } else {
                option
            }
        }

        val optionsForFirebase = voteValue.map {
            mapOf("name" to it.name, "count" to it.count)
        }

        db.collection("vote").document(voteId)
            .update("Options", optionsForFirebase)
    }
}