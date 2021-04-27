package com.bhaktaprogram.coreimpl.repository

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.database.NO_ID
import com.bhaktaprogram.database.NoteEntity

fun NoteEntity.toDomain() = Note(
    id = id,
    title = title,
    text = text,
)

fun Note.toDb() = NoteEntity(
    id = if (id < 0) NO_ID else id,
    title = title,
    text = text
)