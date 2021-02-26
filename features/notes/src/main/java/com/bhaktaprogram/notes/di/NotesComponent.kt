package com.bhaktaprogram.notes.di

import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notes.domain.NotesRepository
import com.bhaktaprogram.notes.repository.NotesRepositoryStub
import com.bhaktaprogram.notes.ui.NotesFragment
import com.bhaktaprogram.notes.ui.NotesViewModelFactory
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