package com.bhaktaprogram.notehelper.features.notes.domain

interface NotesRepository {

    suspend fun getAll(): List<NoteInfo>
}