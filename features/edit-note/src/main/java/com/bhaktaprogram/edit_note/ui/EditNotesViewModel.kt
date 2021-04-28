package com.bhaktaprogram.edit_note.ui

import androidx.lifecycle.*
import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.edit_note.domain.EditNoteInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNotesViewModel @Inject constructor(
    private val interactor: EditNoteInteractor
) : ViewModel(), LifecycleObserver {

    private var note = Note.emptyInstance()

    private val _state = MutableStateFlow(EditNoteUi.createEmpty())
    val state: StateFlow<EditNoteUi> = _state.asStateFlow()

    fun onOpened(noteId: Int) {
        viewModelScope.launch {
            if (note.isEmpty() && noteId != -1) {
                note = interactor.getNote(noteId)
            }
            _state.value = note.toUi()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        // TODO should use scope passed to constructor to testing
        CoroutineScope(IO).launch {
            interactor.save(note)
        }
    }

    fun onTitleChanged(title: String) {
        note = note.copy(title = title)
    }

    fun onTextChanged(text: String) {
        note = note.copy(text = text)
    }
}