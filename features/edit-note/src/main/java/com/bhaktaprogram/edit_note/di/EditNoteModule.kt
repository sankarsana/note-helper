package com.bhaktaprogram.edit_note.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.edit_note.ui.EditNoteViewModelFactory
import com.bhaktaprogram.edit_note.ui.EditNotesViewModel
import dagger.Binds
import dagger.Module

@Module
interface EditNoteModule {

    @Binds
    fun bindViewModelFactory(factory: EditNoteViewModelFactory<EditNotesViewModel>): ViewModelProvider.Factory
}