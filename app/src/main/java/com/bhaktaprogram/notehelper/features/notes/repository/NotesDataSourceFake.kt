package com.bhaktaprogram.notehelper.features.notes.repository

import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import javax.inject.Inject

class NotesDataSourceFake @Inject constructor() : NotesDataSource {

    override suspend fun getAll(): List<NoteInfo> {
        return listOf(
            NoteInfo(1, "First"),
            NoteInfo(2, "Second"),
            NoteInfo(3, "Third")
        )
    }
}