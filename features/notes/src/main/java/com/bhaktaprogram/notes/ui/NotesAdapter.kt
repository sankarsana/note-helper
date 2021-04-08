package com.bhaktaprogram.notes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhaktaprogram.notes.databinding.NoteItemBinding

class NotesAdapter(private val clickListener: (NoteInfoUiDto) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.Holder>() {

    private var items = emptyList<NoteInfoUiDto>()

    fun update(items: List<NoteInfoUiDto>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
        return Holder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class Holder(
        private val binding: NoteItemBinding,
        private val clickListener: (NoteInfoUiDto) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteInfoUiDto) {
            binding.id.text = item.id
            binding.title.text = item.title
            itemView.setOnClickListener { clickListener(item) }
        }
    }
}