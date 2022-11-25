package com.virtualica.mobile_android

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.virtualica.mobile_android.models.dataClasses.Themes

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MytopicsRecyclerViewAdapter(
    private val itemClickListener: OnItemClickListener,
    themes: MutableList<Themes>,
    color: String,
):
    RecyclerView.Adapter<MytopicsRecyclerViewAdapter.TopicViewHolder>() {

    private val dataThemes = themes
    private val colSel = color

    inner class TopicViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView = itemView.findViewById(R.id.topicTopicView)
        var percentageDone: TextView = itemView.findViewById(R.id.progressPrecentageTopic)
        var percentageNum: ProgressBar = itemView.findViewById(R.id.progressBarTopic)
        var style = itemView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.onItemClick(title.text.toString())
        }

    }
    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val gradientDrawable : GradientDrawable = ContextCompat.getDrawable(holder.style.context, R.drawable.shapestopic) as GradientDrawable
        gradientDrawable.setTint(Color.parseColor(colSel))
        holder.style.background = gradientDrawable
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
        fun onItemClick(name : String)
    }

}