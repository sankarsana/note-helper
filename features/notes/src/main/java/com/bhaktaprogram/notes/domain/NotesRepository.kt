package com.bhaktaprogram.notes.domain

interface NotesRepository {

    suspend fun getAll(): List<NoteInfo>
}