package com.bhaktaprogram.coreimpl.di

import com.bhaktaprogram.coreapi.repository.NoteRepository
import com.bhaktaprogram.coreimpl.repository.NotesRepositoryStub
import dagger.Binds
import dagger.Module

@Module
interface CoreModule {

    @Binds
    fun bindNotesRepository(repo: NotesRepositoryStub): NoteRepository
}