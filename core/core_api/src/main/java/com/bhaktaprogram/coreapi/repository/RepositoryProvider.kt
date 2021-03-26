package com.bhaktaprogram.coreapi.repository

interface RepositoryProvider {

    fun getNotesRepository(): NoteRepository
}