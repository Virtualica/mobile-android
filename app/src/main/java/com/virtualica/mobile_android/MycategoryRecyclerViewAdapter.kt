package com.virtualica.mobile_android

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Category
import com.virtualica.mobile_android.models.dataClasses.Themes
import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MycategoryRecyclerViewAdapter(
    private val itemClickListener: OnItemClickListener,
    categories: MutableList<Category>,
    progressBar6: ProgressBar
):
    RecyclerView.Adapter<MycategoryRecyclerViewAdapter.CategoryViewHolder>(){

    private val colors = arrayOf("#E63222","#92106D","#524FD8","#3AD89F","#FDCE20")
    private val backColors = arrayOf("#FFEBEA","#E5D8E8","#F5F5FE","#DFFFF4","#F3E5C4")
    private val dataCategories = categories
    private val progressGet = progressBar6


    inner class CategoryViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView = itemView.findViewById(R.id.titleCategoryView)
        var text: TextView = itemView.findViewById(R.id.categoryText)
        var percentageNum: TextView = itemView.findViewById(R.id.categoryPercentage)
        var progressBar : ProgressBar = itemView.findViewById(R.id.progressBarCategory)
        var style = itemView


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val db = Firebase.firestore
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                val fragment = TopicsFragment()
                val bundle = Bundle()
                val select = dataCategories[position].nombre
                var count = 0
                progressGet.visibility = View.VISIBLE
                db.collection("temas").whereEqualTo("categoria", select).get().addOnSuccessListener { res ->
                    for (t in res){
                        val newT = t.toObject(Themes::class.java).also {
                            it.id = t.id
                        }
                        bundle.putSerializable("theme$count", newT)
                        count++
                    }
                    bundle.putString("color", colors[position])
                    fragment.arguments = bundle
                    itemClickListener.onItemClick(fragment)
                    progressGet.visibility = View.INVISIBLE
                }
            }
        }
    }


    interface OnItemClickListener{
        fun onItemClick(fragment: Fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val layerDrawable : LayerDrawable = ContextCompat.getDrawable(holder.style.context, R.drawable.trapecio) as LayerDrawable
        layerDrawable.getDrawable(0).setTint(Color.parseColor(colors[position]))
        layerDrawable.getDrawable(1).setTint(Color.parseColor(backColors[position]))
        holder.style.background = layerDrawable

        holder.progressBar.progressTintList = ColorStateList.valueOf(Color.parseColor(colors[position]))
        holder.title.text = dataCategories[position].nombre
        holder.text.text = dataCategories[position].desc
        holder.percentageNum.text = dataCategories[position].percentage
        holder.progressBar.setProgress(Integer.parseInt(dataCategories[position].percentage), true)
    }

    override fun getItemCount(): Int {
        return dataCategories.size
    }
}

