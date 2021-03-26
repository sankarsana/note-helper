package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository

class NoteRepositoryImpl : NoteRepository {

    override suspend fun getNotesInfo(): List<NoteInfo> {
        TODO("Not yet implemented")
    }
}