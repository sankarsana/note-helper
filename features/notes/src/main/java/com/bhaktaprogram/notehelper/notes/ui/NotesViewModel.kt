package com.bhaktaprogram.notehelper.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.notehelper.notes.domain.NoteInfo
import com.bhaktaprogram.notehelper.notes.domain.NotesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val interactor: NotesInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<List<NoteInfo>>(emptyList())
    val state: StateFlow<List<NoteInfo>> get() = _state

    init {
        viewModelScope.launch {
            _state.value = interactor.getAll()
        }
    }
}