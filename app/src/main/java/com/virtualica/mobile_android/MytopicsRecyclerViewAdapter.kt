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
class MytopicsRecyclerViewAdapter:
    RecyclerView.Adapter<MytopicsRecyclerViewAdapter.TopicViewHolder>() {

    inner class TopicViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

    }
    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}