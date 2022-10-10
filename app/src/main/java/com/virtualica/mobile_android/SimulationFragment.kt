package com.virtualica.mobile_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 * Use the [SimulationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimulationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.simulation, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = SimulationFragment()
    }
}