package com.bhaktaprogram.edit_note.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.edit_note.domain.EditNoteInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNotesViewModel @Inject constructor(
    private val interactor: EditNoteInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(EditNoteUi.createEmpty())
    val state: StateFlow<EditNoteUi> = _state.asStateFlow()

    fun onOpened(noteId: Int) {
        viewModelScope.launch {
            val note = interactor.getNote(noteId)
            _state.value = note.toUi()
        }
    }
}