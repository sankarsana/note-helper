package com.bhaktaprogram.coreapi

interface MediatorsProvider {

    fun provideNotesMediator(): NotesMediator
}