package com.bhaktaprogram.notehelper.ui

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.bhaktaprogram.notehelper.databinding.ActivityMainBinding
import com.bhaktaprogram.notehelper.repository.IconApiProvider
import com.bhaktaprogram.notehelper.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { IconAdapter() }
    private val repository = Repository(IconApiProvider().create())
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.adapter = adapter
        binding.query.doAfterTextChanged(::onSearchTextChanged)
    }

    private fun onSearchTextChanged(editable: Editable?) {
        if (editable == null) return
        val text = editable.toString()
        if (text.length < 3) {
            adapter.update(emptyList())
        } else {
            loadIcons(text)
        }
    }

    private fun loadIcons(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(DELAY_BEFORE_SEARCH)
            val data = repository.searchIcons(query)
            adapter.update(data)
        }
    }

    companion object {
        private const val DELAY_BEFORE_SEARCH = 500L
    }
}