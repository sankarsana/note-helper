package com.bhaktaprogram.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notes.ui.NotesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface NotesModule {

    @Binds
    fun bindViewModelFactory(factory: NotesViewModelFactory): ViewModelProvider.Factory
}