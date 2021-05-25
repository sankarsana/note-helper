package com.bhaktaprogram.notehelper

import com.bhaktaprogram.coreapi.navigation.EditNoteRouter
import com.bhaktaprogram.coreapi.navigation.NotesRouter
import com.bhaktaprogram.edit_note.ui.EditNoteRouterImpl
import com.bhaktaprogram.notes.ui.NotesRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface RoutersModule {

    @Binds
    fun bindEditNote(router: EditNoteRouterImpl): EditNoteRouter

    @Binds
    fun bindNotes(router: NotesRouterImpl): NotesRouter
}