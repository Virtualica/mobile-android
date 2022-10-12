package com.virtualica.mobile_android

import android.content.Intent
  import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigator
import kotlinx.android.synthetic.main.bottom_bar.*

import kotlinx.android.synthetic.main.practice.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [TrainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.practice,container,false)

        view.back_practice.setOnClickListener(){
            val topicsFragment = topicsFragment()
            val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer,topicsFragment)
            transaction?.commit()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        @JvmStatic
        fun newInstance() = TrainFragment()
    }
}