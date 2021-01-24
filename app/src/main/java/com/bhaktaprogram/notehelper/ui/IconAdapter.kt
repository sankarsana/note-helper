package com.bhaktaprogram.notehelper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhaktaprogram.notehelper.R
import com.bhaktaprogram.notehelper.databinding.ListItemBinding
import com.bumptech.glide.Glide

class IconAdapter : RecyclerView.Adapter<IconAdapter.Holder>() {

    private var items = emptyList<IconView>()

    fun update(items: List<IconView>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        val binding = ListItemBinding.bind(view)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class Holder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IconView) = with(binding) {
            idValue.text = item.id.toString()
            tagsValue.text = item.tags
            Glide
                .with(binding.root.context)
                .load(item.url)
                .into(icon)
        }
    }
}