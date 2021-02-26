package com.bhaktaprogram.notes.domain

import javax.inject.Inject

class NotesInteractor @Inject constructor(
    private val repository: NotesRepository
) {
    suspend fun getAll(): List<NoteInfo> = repository.getAll()
}