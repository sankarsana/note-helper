package com.bhaktaprogram.coreapi.navigation

interface RoutersProvider {

    fun provideMain(): MainRouter

    fun provideNotes(): NotesRouter
}