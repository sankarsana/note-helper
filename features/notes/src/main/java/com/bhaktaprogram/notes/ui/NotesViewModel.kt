package com.bhaktaprogram.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.navigation.EditNoteRouter
import com.bhaktaprogram.notes.domain.NotesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val interactor: NotesInteractor,
    private val router: EditNoteRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<List<NoteInfoUi>>(emptyList())
    val state: StateFlow<List<NoteInfoUi>> get() = _state

    fun onOpened() {
        viewModelScope.launch {
            _state.value = interactor.getAll()
                .map { it.toUi() }
        }
    }

    private fun NoteInfo.toUi() = NoteInfoUi(
        id = id.toString(),
        title = title,
        text = text
    )

    fun onNoteClicked(note: NoteInfoUi) {
        router.show(note.id.toInt())
    }

    fun onAddClicked() {
        router.show(-1)
    }
}