package com.virtualica.mobile_android

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.virtualica.mobile_android.databinding.TopicListBinding
import com.virtualica.mobile_android.models.dataClasses.Themes

/**
 * A fragment representing a list of Items.
 */
class TopicsFragment : Fragment(),MytopicsRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 3
    private var _binding : TopicListBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val themes : MutableList<Themes> = ArrayList()
        val dataThemes = arguments
        for (i in 0 until dataThemes!!.size()-1){
            val t : Themes = dataThemes.get("theme$i") as Themes
            themes.add(t)
        }
        val color = dataThemes.get("color").toString()
        _binding = TopicListBinding.inflate(inflater, container, false)
        val recycler = binding.listInCategory
        val adapter = MytopicsRecyclerViewAdapter(this, themes, color)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(activity, columnCount)
        recycler.adapter = adapter
        setupOnBackPressed()

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = TopicsFragment()
    }

    override fun onItemClick(name : String) {
        val intent = Intent(this@TopicsFragment.requireContext(),TrainActivity::class.java).apply {
            putExtra("topic", name)
        }
        startActivity(intent)
    }

    private fun setupOnBackPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(isEnabled){
                    val test : FragmentActivity = requireActivity() as FragmentActivity
                    test.showFragment()
                }
            }
        })
    }


}