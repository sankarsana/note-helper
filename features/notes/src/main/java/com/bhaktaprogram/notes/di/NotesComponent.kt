package com.bhaktaprogram.notes.di

import com.bhaktaprogram.coreapi.AppFacade
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

        fun create(appFacade: AppFacade): NotesComponent =
            DaggerNotesComponent.builder()
                .providersFacade(appFacade.getProvidersFacade())
                .build()
    }
}