package com.bhaktaprogram.notehelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.notehelper.features.notes.domain.NotesRepository
import com.bhaktaprogram.notehelper.features.notes.ui.NotesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: NotesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragments_container, NotesFragment())
                .commit()
        }

    }
}