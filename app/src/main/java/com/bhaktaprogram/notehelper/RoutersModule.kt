package com.bhaktaprogram.notehelper

import com.bhaktaprogram.coreapi.navigation.MainRouter
import com.bhaktaprogram.coreapi.navigation.NotesRouter
import com.bhaktaprogram.main.MainRouterImpl
import com.bhaktaprogram.notes.ui.NotesRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface RoutersModule {

    @Binds
    fun bindMainRouter(router: MainRouterImpl): MainRouter

    @Binds
    fun bindNotesRouter(router: NotesRouterImpl): NotesRouter
}