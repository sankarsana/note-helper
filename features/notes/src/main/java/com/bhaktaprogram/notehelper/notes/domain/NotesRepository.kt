package com.bhaktaprogram.notehelper.notes.domain

interface NotesRepository {

    suspend fun getAll(): List<NoteInfo>
}