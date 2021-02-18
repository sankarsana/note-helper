package com.bhaktaprogram.notehelper.features.notes.repository

import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo

interface NotesDataSource {

    suspend fun getAll(): List<NoteInfo>
}