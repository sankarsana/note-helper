package com.bhaktaprogram.edit_note.ui

data class EditNoteUi(val id: Int, val title: String, val text: String) {

    companion object {
        fun createEmpty() = EditNoteUi(-1, "", "")
    }
}
