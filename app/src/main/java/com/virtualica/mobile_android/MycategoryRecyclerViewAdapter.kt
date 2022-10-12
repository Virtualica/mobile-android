package com.virtualica.mobile_android

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.virtualica.mobile_android.placeholder.PlaceholderContent.PlaceholderItem
import com.virtualica.mobile_android.databinding.FragmentItemBinding
import org.w3c.dom.Text

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MycategoryRecyclerViewAdapter(private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<MycategoryRecyclerViewAdapter.CategoryViewHolder>(){

    private val titleCat = arrayOf("Matematicas", "Español", "Ciencias Naturales", "Ciencias Sociales", "Ingles")
    private val textCat = arrayOf(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
    private val percentageCat = arrayOf("10%","20%","30%","40%","50%")
    private val progressBarCat = arrayOf("10","20","30","40","50")

    inner class CategoryViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title: TextView = itemView.findViewById(R.id.titleCategoryView)
        var text: TextView = itemView.findViewById(R.id.categoryText)
        var percentageNum: TextView = itemView.findViewById(R.id.categoryPercentage)
        var progressBar : ProgressBar = itemView.findViewById(R.id.progressBarCategory)
        //var ttitle: TextView = itemView.findViewById<TextView>(R.id.textView5)
        //var ttext: TextView = itemView.findViewById<TextView>(R.id.textView6)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){

                //ttitle.text = "Temas de la asignatura"
                //ttext.text = "Disftuta de tu estudio"

                itemClickListener.onItemClick(topicsFragment.newInstance())

            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(fragment: Fragment)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = titleCat[position]
        holder.text.text = textCat[position]
        holder.percentageNum.text = percentageCat[position]
        holder.progressBar.progress = Integer.parseInt(progressBarCat[position])
    }

    override fun getItemCount(): Int {
        return titleCat.size
    }
}
