package com.bhaktaprogram.notehelper.notes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bhaktaprogram.notehelper.notes.R
import com.bhaktaprogram.notehelper.notes.databinding.NotesFragmentBinding
import com.bhaktaprogram.notehelper.notes.di.DaggerNotesComponent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotesViewModel
    private var _binding: NotesFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = NotesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerNotesComponent.create().inject(this)

        _binding = NotesFragmentBinding.bind(view)
        binding.list.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NotesViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::updateState)
        }
    }

    private fun updateState(items: List<NoteInfoUiDto>) {
        adapter.update(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}