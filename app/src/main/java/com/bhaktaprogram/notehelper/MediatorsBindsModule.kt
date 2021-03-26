package com.bhaktaprogram.notehelper

import com.bhaktaprogram.coreapi.NotesMediator
import com.bhaktaprogram.notes.ui.NotesMediatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MediatorsBindsModule {

    @Binds
    fun bindsNotesMediator(notesMediator: NotesMediatorImpl): NotesMediator
}