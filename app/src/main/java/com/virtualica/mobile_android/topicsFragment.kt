package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.virtualica.mobile_android.databinding.TopicListBinding
import com.virtualica.mobile_android.models.Themes
import com.virtualica.mobile_android.placeholder.PlaceholderContent
import kotlinx.android.synthetic.main.bottom_bar.*

/**
 * A fragment representing a list of Items.
 */
class topicsFragment : Fragment(),MytopicsRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 3
    private lateinit var elementsList: ListView
    private var _binding : TopicListBinding? = null
    private val  binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val themes : MutableList<Themes> = ArrayList()
        val dataThemes = arguments
        for (i in 0 until dataThemes!!.size()){
            val t : Themes = dataThemes.get("theme$i") as Themes
            themes.add(t)
        }
        _binding = TopicListBinding.inflate(inflater, container, false)
        val adapter = MytopicsRecyclerViewAdapter(this, themes)
        val recycler = binding.listInCategory
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(activity, columnCount)
        recycler.adapter = adapter
        return binding.root
    }

    /*
            val categories : MutableList<Category> = ArrayList()
        val dataCategory = arguments
        for (i in 0 until dataCategory!!.size()){
            val c : Category = dataCategory.get("category$i") as Category
            categories.add(c)
        }
        _binding = CategoryListBinding.inflate(inflater, container, false)
        val adapter = MycategoryRecyclerViewAdapter(this, categories)
        val recycler = binding.listInCategory
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter

        return binding.root
     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = topicsFragment()
    }

    override fun onItemClick() {
        val intent = Intent(this@topicsFragment.requireContext(),TrainActivity::class.java)
        startActivity(intent)
       // fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, fragment)?.commit()

       // Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show()
    }
}