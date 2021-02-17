package com.bhaktaprogram.notehelper.features.notes

interface NotesDataSource {

    suspend fun getAll(): List<NoteInfo>
}