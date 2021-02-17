package com.bhaktaprogram.notehelper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.notehelper.di.DaggerNotesComponent
import com.bhaktaprogram.notehelper.features.notes.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: NotesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerNotesComponent.create().inject(this)

        CoroutineScope(Default).launch {
            val notes = repository.getAll()

            if (notes.isEmpty()) throw RuntimeException()

            Log.i("TAG", notes.toString())
        }
    }
}