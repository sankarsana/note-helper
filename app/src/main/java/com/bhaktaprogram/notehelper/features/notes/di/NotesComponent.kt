package com.bhaktaprogram.notehelper.features.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import com.bhaktaprogram.notehelper.features.notes.repository.NotesDataSource
import com.bhaktaprogram.notehelper.features.notes.repository.NotesDataSourceFake
import com.bhaktaprogram.notehelper.features.notes.repository.NotesRepositoryImpl
import com.bhaktaprogram.notehelper.features.notes.ui.NotesFragment
import com.bhaktaprogram.notehelper.features.notes.ui.NotesViewModelFactory
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(modules = [NotesModule::class])
interface NotesComponent {

    fun inject(fragment: NotesFragment)
}

@Module
abstract class NotesModule {

    @Binds
    abstract fun bindViewModelFactory(factory: NotesViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRepository(repository: NotesRepositoryImpl): NotesRepository

    @Binds
    abstract fun bindDataSource(dataSource: NotesDataSourceFake): NotesDataSource
}