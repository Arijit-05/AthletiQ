package com.example.fitnessapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.data.SearchItem

class SearchAdapter(
    private var items: List<SearchItem>,
    private val onItemClick: (SearchItem) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTv: TextView = view.findViewById(R.id.title_tv)
        val descriptionTv: TextView = view.findViewById(R.id.description_tv)
        val typeTv: TextView = view.findViewById(R.id.type_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]
        holder.titleTv.text = item.title
        holder.descriptionTv.text = item.description
        holder.typeTv.text = item.type.capitalize()
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = items.size

    fun updateList(newList: List<SearchItem>) {
        items = newList
        notifyDataSetChanged()
    }
} 