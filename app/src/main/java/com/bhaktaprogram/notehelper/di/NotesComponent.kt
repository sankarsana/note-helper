package com.bhaktaprogram.notehelper.di

import com.bhaktaprogram.notehelper.MainActivity
import dagger.Component

@Component(modules = [NotesModule::class])
interface NotesComponent {

    fun inject(activity: MainActivity)
}