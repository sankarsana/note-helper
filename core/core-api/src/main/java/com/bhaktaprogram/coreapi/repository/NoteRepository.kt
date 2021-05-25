package com.bhaktaprogram.coreapi.repository

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.coreapi.dto.NoteInfo

interface NoteRepository {

    suspend fun getNotesInfo(): List<NoteInfo>

    suspend fun getById(noteId: Int): Note

    suspend fun save(note: Note)
}