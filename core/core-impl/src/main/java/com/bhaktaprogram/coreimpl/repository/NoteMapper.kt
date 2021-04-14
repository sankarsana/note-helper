package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.database.database.NoteEntity

fun NoteEntity.toDomain() = Note(
    id = id,
    title = title,
    text = text,
)