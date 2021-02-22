package com.bhaktaprogram.notehelper.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notehelper.notes.domain.NotesRepository
import com.bhaktaprogram.notehelper.notes.repository.NotesRepositoryStub
import com.bhaktaprogram.notehelper.notes.ui.NotesFragment
import com.bhaktaprogram.notehelper.notes.ui.NotesViewModelFactory
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
    abstract fun bindRepository(repository: NotesRepositoryStub): NotesRepository
}