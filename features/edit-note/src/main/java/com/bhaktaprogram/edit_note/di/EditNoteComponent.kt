package com.bhaktaprogram.edit_note.di

import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.edit_note.ui.EditNotesFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [EditNoteModule::class]
)
interface EditNoteComponent {

    fun inject(fragment: EditNotesFragment)

    companion object {

        fun create(appFacade: AppFacade): EditNoteComponent =
            DaggerEditNoteComponent.builder()
                .providersFacade(appFacade.getProvidersFacade())
                .build()
    }
}