package com.bhaktaprogram.notes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bhaktaprogram.coreapi.extensions.getProvidersFacade
import com.bhaktaprogram.notes.R
import com.bhaktaprogram.notes.databinding.NotesFragmentBinding
import com.bhaktaprogram.notes.di.NotesComponent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment), NotesRouter {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotesViewModel
    private val binding by viewBinding(NotesFragmentBinding::bind)
    private val adapter = NotesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotesComponent
            .create(this, requireActivity().getProvidersFacade())
            .inject(this)

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

    override fun openEditScreen(noteId: Int) {
        val mediator = requireActivity().getProvidersFacade().provideNotesMediator()
        mediator.startNotes(R.id.id, childFragmentManager)
    }
}