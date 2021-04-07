package com.bhaktaprogram.notes.di

import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.notes.ui.NotesFragment
import com.bhaktaprogram.notes.ui.NotesRouter
import dagger.Component

@Component(
    modules = [NotesModule::class],
    dependencies = [ProvidersFacade::class, NotesRouter::class]
)
interface NotesComponent {

    fun inject(fragment: NotesFragment)

    companion object {

        fun create(notesRouter: NotesRouter, providersFacade: ProvidersFacade): NotesComponent =
            DaggerNotesComponent
                .builder()
                .notesRouter(notesRouter)
                .providersFacade(providersFacade)
                .build()
    }
}