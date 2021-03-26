package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository
import javax.inject.Inject

class NotesRepositoryStub @Inject constructor() : NoteRepository {

    override suspend fun getNotesInfo(): List<NoteInfo> = listOf(
        NoteInfo(1, "First important note"),
        NoteInfo(2, "Second important note"),
        NoteInfo(3, "Third important note")
    )
}