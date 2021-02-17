package com.bhaktaprogram.notehelper.features.notes

interface NotesRepository {

    suspend fun getAll(): List<NoteInfo>
}