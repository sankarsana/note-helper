package com.bhaktaprogram.notehelper.features.notes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bhaktaprogram.notehelper.R
import com.bhaktaprogram.notehelper.databinding.NotesFragmentBinding
import com.bhaktaprogram.notehelper.features.notes.di.DaggerNotesComponent
import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotesViewModel
    private var _binding: NotesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerNotesComponent.create().inject(this)

        _binding = NotesFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NotesViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::updateState)
        }
    }

    private fun updateState(list: List<NoteInfo>) {
        val notes = StringBuilder("Notes:\n")
        list.forEach {
            notes.append("\t").append(it.toString()).append("\n")
        }
        notes.trim()
        binding.textView.text = notes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}