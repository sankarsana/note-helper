package com.bhaktaprogram.coreapi.dto

data class Note(
    val id: Int,
    val title: String,
    val text: String,
) {
    fun isEmpty(): Boolean {
        return id == -1 && title.isBlank() && text.isBlank()
    }

    companion object {
        fun emptyInstance() = Note(-1, "", "")
    }
}