package com.bhaktaprogram.coreapi.navigation

interface RoutersProvider {

    fun provideNotes(): NotesRouter

    fun provideEditNotes(): EditNoteRouter
}