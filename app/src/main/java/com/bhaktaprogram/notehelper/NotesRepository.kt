package com.bhaktaprogram.notehelper

interface NotesRepository {

    suspend fun getAll(): List<NoteInfo>
}