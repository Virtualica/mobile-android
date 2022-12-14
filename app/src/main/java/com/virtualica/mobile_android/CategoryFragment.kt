package com.virtualica.mobile_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualica.mobile_android.databinding.CategoryListBinding
import com.virtualica.mobile_android.models.dataClasses.Category


class CategoryFragment : Fragment(), MycategoryRecyclerViewAdapter.OnItemClickListener {

    private var _binding: CategoryListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categories : MutableList<Category> = ArrayList()
        val dataCategory = arguments
        for (i in 0 until dataCategory!!.size()){
            val c : Category = dataCategory.get("category$i") as Category
            categories.add(c)
        }
        _binding = CategoryListBinding.inflate(inflater, container, false)
        val recycler = binding.listInCategory
        val adapter = MycategoryRecyclerViewAdapter(this, categories, binding.progressBar6)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = adapter


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }


    override fun onItemClick(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, fragment)?.commit()
    }
}


