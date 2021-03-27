package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository
import com.bhaktaprogram.repository.database.NotesDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NoteRepository {

    override suspend fun getNotesInfo(): List<NoteInfo> {
        return notesDao.getAll().map { NoteInfo(it.id, it.title) }
    }
}