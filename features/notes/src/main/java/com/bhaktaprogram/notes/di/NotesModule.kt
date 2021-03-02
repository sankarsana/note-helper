package com.bhaktaprogram.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notes.domain.NotesRepository
import com.bhaktaprogram.notes.repository.NotesRepositoryStub
import com.bhaktaprogram.notes.ui.NotesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface NotesModule {

    @Binds
    fun bindViewModelFactory(factory: NotesViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindRepository(repository: NotesRepositoryStub): NotesRepository
}