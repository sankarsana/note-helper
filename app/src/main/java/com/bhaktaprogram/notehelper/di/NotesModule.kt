package com.bhaktaprogram.notehelper.di

import com.bhaktaprogram.notehelper.NotesDataSource
import com.bhaktaprogram.notehelper.NotesDataSourceFake
import com.bhaktaprogram.notehelper.NotesRepository
import com.bhaktaprogram.notehelper.NotesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NotesModule {

    @Binds
    fun bindRepository(repository: NotesRepositoryImpl): NotesRepository

    @Binds
    fun bindDataSource(dataSource: NotesDataSourceFake): NotesDataSource
}