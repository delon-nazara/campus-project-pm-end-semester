package com.example.proyekakhirpemrogramanmobile.ui.screen.CreateVote

import androidx.lifecycle.ViewModel
import com.example.proyekakhirpemrogramanmobile.domain.Voting.Vote
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateVoteViewModel() : ViewModel() {
    private val db = Firebase.firestore

    private val _isSubmitting = MutableStateFlow(false)
    var isSubmitting = _isSubmitting.asStateFlow()

    fun createVote(title: String, options: List<Vote>, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        _isSubmitting.value = true

        val newVote = mapOf(
            "Title" to title,
            "Options" to options.map { option ->
                mapOf("name" to option.name, "count" to option.count)
            }
        )

        db.collection("Votes")
            .add(newVote)
            .addOnSuccessListener {
                _isSubmitting.value = false
                onSuccess()
            }
            .addOnFailureListener { exception ->
                _isSubmitting.value = false
                onError(exception)
            }
    }
}