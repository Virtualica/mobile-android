package com.virtualica.mobile_android

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem
import com.virtualica.mobile_android.databinding.FragmentItem2Binding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MytopicsRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MytopicsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItem2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.textView7
        val contentView: TextView = binding.textView8

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}