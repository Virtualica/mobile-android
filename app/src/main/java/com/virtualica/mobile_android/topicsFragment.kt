package com.virtualica.mobile_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.virtualica.mobile_android.databinding.FragmentItemListBinding
import com.virtualica.mobile_android.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class topicsFragment : Fragment() {

    private var columnCount = 3
    private lateinit var elementsList: ListView
    private var _binding : FragmentItemListBinding? = null
    private val  binding get() = _binding!!

    private val adapter = MytopicsRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        val recycler = binding.listInCategory
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(activity, columnCount)
        recycler.adapter = adapter


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = topicsFragment()
    }
}