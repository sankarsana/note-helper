package com.bhaktaprogram.notehelper.notes.repository

import com.bhaktaprogram.notehelper.notes.domain.NoteInfo
import com.bhaktaprogram.notehelper.notes.domain.NotesRepository
import javax.inject.Inject

class NotesRepositoryStub @Inject constructor() : NotesRepository {

    override suspend fun getAll(): List<NoteInfo> = listOf(
        NoteInfo(1, "First"),
        NoteInfo(2, "Second"),
        NoteInfo(3, "Third")
    )
}