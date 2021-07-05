package com.bhaktaprogram.notes.di

import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.notes.ui.NotesFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [NotesModule::class],
)
interface NotesComponent {

    fun inject(fragment: NotesFragment)

    companion object {

        fun create(providersFacade: ProvidersFacade): NotesComponent {
            return DaggerNotesComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
}