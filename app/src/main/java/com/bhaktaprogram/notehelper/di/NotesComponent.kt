package com.bhaktaprogram.notehelper.di

import com.bhaktaprogram.notehelper.*
import dagger.Binds
import dagger.Component
import dagger.Module

@Component(modules = [NotesModule::class])
interface NotesComponent {

    fun inject(activity: MainActivity)
}

@Module
abstract class NotesModule {

    @Binds
    abstract fun bindRepository(repository: NotesRepositoryImpl): NotesRepository

    @Binds
    abstract fun bindDataSource(dataSource: NotesDataSourceFake): NotesDataSource
}