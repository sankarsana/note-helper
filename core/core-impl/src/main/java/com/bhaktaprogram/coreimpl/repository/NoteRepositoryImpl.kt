package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository
import com.bhaktaprogram.database.NO_ID
import com.bhaktaprogram.database.NotesDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NoteRepository {

    override suspend fun getNotesInfo(): List<NoteInfo> {
        return notesDao.getAll().map { NoteInfo(it.id, it.title, it.text) }
    }

    override suspend fun getById(noteId: Int): Note {
        return notesDao.getById(noteId).toDomain()
    }

    override suspend fun save(note: Note) {
        val noteEntity = note.toDb()
        if (noteEntity.id == NO_ID) {
            notesDao.insert(noteEntity)
        } else {
            notesDao.update(noteEntity)
        }
    }
}