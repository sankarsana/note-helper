package com.bhaktaprogram.notes.domain

import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository
import javax.inject.Inject

class NotesInteractor @Inject constructor(
    private val repository: NoteRepository
) {
    suspend fun getAll(): List<NoteInfo> = repository.getNotesInfo()
}