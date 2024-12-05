package com.example.proyekakhirpemrogramanmobile.ui.screen.Voting

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.domain.Voting.Vote
import com.example.proyekakhirpemrogramanmobile.domain.Voting.VoteList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VotingViewModel() : ViewModel() {
    private var db = Firebase.firestore
    private val voteId: String = "CeXHVIldmERBJ5shEhid"

    private var _votes = MutableStateFlow<VoteList?>(null)
    var votes = _votes.asStateFlow()

    init {
        fetchVote()
    }

    private fun fetchVote(){
        db.collection("Votes").document(voteId)
            .addSnapshotListener{ snapshot, exception ->
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

    fun updateVote(name: String) {
        val currentVote = _votes.value ?: return
        val voteValue = currentVote.options.map {option ->
            if(option.name == name) {
                option.copy(count = option.count + 1)
            }
            else {
                option
            }
        }

        val optionsForFirebase = voteValue.map {
            mapOf("name" to it.name, "count" to it.count)
        }

        db.collection("Votes").document(voteId)
            .update("Options", optionsForFirebase)
    }
}