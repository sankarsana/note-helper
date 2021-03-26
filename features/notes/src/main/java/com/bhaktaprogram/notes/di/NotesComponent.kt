package com.bhaktaprogram.notes.di

import com.bhaktaprogram.notes.ui.NotesFragment
import dagger.Component

@Component(modules = [NotesModule::class])
interface NotesComponent {

    fun inject(fragment: NotesFragment)
}