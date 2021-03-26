package com.bhaktaprogram.notes.repository

import com.bhaktaprogram.notes.domain.NoteInfo
import com.bhaktaprogram.notes.domain.NotesRepository
import javax.inject.Inject

class NotesRepositoryStub @Inject constructor() : NotesRepository {

    override suspend fun getAll(): List<NoteInfo> = listOf(
        NoteInfo(1, "First important note"),
        NoteInfo(2, "Second important note"),
        NoteInfo(3, "Third important note")
    )
}