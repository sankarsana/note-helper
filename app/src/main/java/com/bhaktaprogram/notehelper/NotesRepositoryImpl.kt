package com.bhaktaprogram.notehelper

import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val localDataSource: NotesDataSource
) : NotesRepository {

    override suspend fun getAll(): List<NoteInfo> {
        return localDataSource.getAll()
    }
}