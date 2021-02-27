package com.bhaktaprogram.notehelper.features.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import com.bhaktaprogram.notehelper.features.notes.repository.NotesDataSource
import com.bhaktaprogram.notehelper.features.notes.repository.NotesDataSourceFake
import com.bhaktaprogram.notehelper.features.notes.repository.NotesRepositoryImpl
import com.bhaktaprogram.notehelper.features.notes.ui.NotesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface NotesModule {

    @Binds
    fun bindViewModelFactory(factory: NotesViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindRepository(repository: NotesRepositoryImpl): NotesRepository

    @Binds
    fun bindDataSource(dataSource: NotesDataSourceFake): NotesDataSource
}