package com.example.proyekakhirpemrogramanmobile.ui.screen.Voting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VotingViewModel : ViewModel() {
    private val _votes = MutableStateFlow(listOf(0, 0, 0))
    val votes: StateFlow<List<Int>> = _votes

    fun vote(optionIndex: Int) {
        _votes.value = _votes.value.mapIndexed { index, count ->
            if (index == optionIndex) count + 1 else count
        }
    }
}