package com.bhaktaprogram.notehelper.features.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<NoteInfo>>(emptyList())
    val state: StateFlow<List<NoteInfo>> get() = _state

    init {
        viewModelScope.launch {
            _state.value = repository.getAll()
        }
    }
}