package com.bhaktaprogram.notehelper.features.notes.repository

import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val localDataSource: NotesDataSource
) : NotesRepository {

    override suspend fun getAll(): List<NoteInfo> {
        return localDataSource.getAll()
    }
}