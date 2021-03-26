package com.bhaktaprogram.coreapi.repository

import com.bhaktaprogram.coreapi.dto.NoteInfo

interface NoteRepository {

    suspend fun getNotesInfo(): List<NoteInfo>
}