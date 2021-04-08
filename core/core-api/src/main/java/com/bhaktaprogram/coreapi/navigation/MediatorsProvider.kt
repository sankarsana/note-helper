package com.bhaktaprogram.coreapi.navigation

interface MediatorsProvider {

    fun provideNotesMediator(): NotesMediator

    fun provideEdinNoteMediator(): EditNoteMediator
}