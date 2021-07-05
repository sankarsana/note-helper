package com.bhaktaprogram.notes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bhaktaprogram.coreapi.extensions.getProviderFacade
import com.bhaktaprogram.notes.R
import com.bhaktaprogram.notes.databinding.NotesFragmentBinding
import com.bhaktaprogram.notes.di.NotesComponent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val binding by viewBinding(NotesFragmentBinding::bind)
    private val viewModel: NotesViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotesComponent
            .create(getProviderFacade())
            .inject(this)

        adapter = NotesAdapter(viewModel::onNoteClicked)
        binding.list.adapter = adapter
        binding.add.setOnClickListener { viewModel.onAddClicked() }

        viewModel.onOpened()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::updateState)
        }
    }

    private fun updateState(items: List<NoteInfoUi>) {
        adapter.update(items)
    }
}