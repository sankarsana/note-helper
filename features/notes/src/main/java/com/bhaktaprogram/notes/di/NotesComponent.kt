package com.bhaktaprogram.notes.di

import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.notes.ui.NotesFragment
import dagger.Component

@Component(
    modules = [NotesModule::class],
    dependencies = [ProvidersFacade::class]
)
interface NotesComponent {

    fun inject(fragment: NotesFragment)

    companion object {

        fun create(providersFacade: ProvidersFacade): NotesComponent =
            DaggerNotesComponent.builder()
                .providersFacade(providersFacade)
                .build()
    }
}