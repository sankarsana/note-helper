package com.bhaktaprogram.notehelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.notehelper.di.DaggerNotesComponent
import com.bhaktaprogram.notehelper.features.notes.NotesFragment
import com.bhaktaprogram.notehelper.features.notes.NotesRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: NotesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerNotesComponent.create().inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragments_container, NotesFragment())
                .commit()
        }

    }
}