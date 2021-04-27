package com.bhaktaprogram.edit_note.domain

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.coreapi.repository.NoteRepository
import javax.inject.Inject

class EditNoteInteractor @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend fun getNote(noteId: Int): Note {
        return noteRepository.getById(noteId)
    }

    suspend fun save(note: Note) {
        noteRepository.save(note)
    }
}