package com.virtualica.mobile_android

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem
import com.virtualica.mobile_android.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */



class MycategoryRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MycategoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = ""
        holder.contentView.text = ""
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.textView2
        val contentView: TextView = binding.textView3

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}