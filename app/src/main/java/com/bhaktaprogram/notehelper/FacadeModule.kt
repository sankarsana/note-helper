package com.bhaktaprogram.notehelper

import com.bhaktaprogram.coreapi.navigation.EditNoteMediator
import com.bhaktaprogram.coreapi.navigation.NotesMediator
import com.bhaktaprogram.edit_note.ui.EditNoteMediatorImpl
import com.bhaktaprogram.notes.ui.NotesMediatorImpl
import dagger.Binds
import dagger.Module

@Module
interface FacadeModule {

    @Binds
    fun notes(notesMediator: NotesMediatorImpl): NotesMediator

    @Binds
    fun editNotes(mediator: EditNoteMediatorImpl): EditNoteMediator
}