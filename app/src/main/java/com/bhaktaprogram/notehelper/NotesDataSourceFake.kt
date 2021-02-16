package com.bhaktaprogram.notehelper

class NotesDataSourceFake : NotesDataSource {

    override suspend fun getAll(): List<NoteInfo> {
        return listOf(
            NoteInfo(1, "First"),
            NoteInfo(2, "Second"),
            NoteInfo(3, "Third")
        )
    }
}