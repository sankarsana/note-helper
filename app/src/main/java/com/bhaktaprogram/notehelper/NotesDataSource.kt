package com.bhaktaprogram.notehelper

interface NotesDataSource {

    suspend fun getAll(): List<NoteInfo>
}