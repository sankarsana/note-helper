package com.bhaktaprogram.edit_note.ui

import com.bhaktaprogram.coreapi.dto.Note

fun Note.toUi() = EditNoteUi(
    id = id,
    title = title,
    text = text,
)