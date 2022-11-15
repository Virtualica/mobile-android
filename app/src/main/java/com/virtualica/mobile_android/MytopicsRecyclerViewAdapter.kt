package com.virtualica.mobile_android

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.virtualica.mobile_android.models.Themes

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MytopicsRecyclerViewAdapter(private val itemClickListener: OnItemClickListener, themes: MutableList<Themes>):
    RecyclerView.Adapter<MytopicsRecyclerViewAdapter.TopicViewHolder>() {

    private val dataThemes = themes

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


        holder.title.text = dataThemes[position].nombre
        holder.percentageDone.text = "100%"
        holder.percentageNum.progress = Integer.parseInt("100")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topic_item, parent, false)
        return TopicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataThemes.size
    }

    interface OnItemClickListener{
        fun onItemClick()
    }

}