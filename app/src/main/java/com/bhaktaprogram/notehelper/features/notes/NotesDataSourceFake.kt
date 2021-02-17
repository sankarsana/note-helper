package com.bhaktaprogram.notehelper.features.notes

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