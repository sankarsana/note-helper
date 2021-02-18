package com.bhaktaprogram.notehelper.features.notes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<NoteInfo>>()
    val state: LiveData<List<NoteInfo>> get() = _state

    init {
        viewModelScope.launch {
            _state.value = repository.getAll()
        }
    }
}