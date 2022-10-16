package com.virtualica.mobile_android

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem
import com.virtualica.mobile_android.databinding.FragmentItem2Binding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MytopicsRecyclerViewAdapter(private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MytopicsRecyclerViewAdapter.TopicViewHolder>() {

    private val titleTopic = arrayOf("Genetica", "ADN", "Dominancia", "Celulas", "Mitosis")
    private val percDon  = arrayOf("10%","20%","30%","40%","50%")
    private val percNum = arrayOf("10","20","30","40","50")

    inner class TopicViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView = itemView.findViewById(R.id.topicTopicView)
        var percentageDone: TextView = itemView.findViewById(R.id.progressPrecentageTopic)
        var percentageNum: ProgressBar = itemView.findViewById(R.id.progressBarTopic)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.onItemClick()
        }

    }
    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.title.text = titleTopic[position]
        holder.percentageDone.text = percDon[position]
        holder.percentageNum.progress = Integer.parseInt(percNum[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item2, parent, false)
        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return percNum.size
    }

    interface OnItemClickListener{
        fun onItemClick()
    }

}