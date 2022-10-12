package com.virtualica.mobile_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualica.mobile_android.databinding.FragmentItemListBinding

class CategoryFragment : Fragment() {

    private lateinit var elementsList: ListView
    private var _binding : FragmentItemListBinding? = null
    private val  binding get() = _binding!!

    private val adapter = MycategoryRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        val recycler = binding.listInCategory
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




    private fun setContentView(fragmentItemList: Int) {

    }


}


