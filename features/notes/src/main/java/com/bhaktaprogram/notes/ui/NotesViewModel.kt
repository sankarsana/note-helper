package com.bhaktaprogram.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktaprogram.notes.domain.NoteInfo
import com.bhaktaprogram.notes.domain.NotesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val interactor: NotesInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<List<NoteInfoUiDto>>(emptyList())
    val state: StateFlow<List<NoteInfoUiDto>> get() = _state

    init {
        viewModelScope.launch {
            _state.value = interactor.getAll()
                .map { it.toUi() }
        }
    }

    private fun NoteInfo.toUi() = NoteInfoUiDto(
        id = id.toString(),
        title = title
    )
}