package com.virtualica.mobile_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * A simple [Fragment] subclass.
 * Use the [TrainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.practice, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TrainFragment()
    }
}