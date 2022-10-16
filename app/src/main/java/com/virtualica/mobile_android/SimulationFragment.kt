package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.virtualica.mobile_android.databinding.SimulationBinding
import kotlinx.android.synthetic.main.simulation.*
import kotlinx.android.synthetic.main.simulation.view.*

class SimulationFragment : Fragment() {




    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
       val view:View = inflater.inflate(R.layout.simulation,container,false)
        view.back_simul.setOnClickListener(){
            MaterialAlertDialogBuilder(this@SimulationFragment.requireContext())
                .setTitle("Nunca es tarde para seguir estudiando, ¿Deseas salir?")
                .setNegativeButton("No ¡Sigamos intentando!"){ _, _ ->

                }
                .setPositiveButton("¡Si!"){ _ , _ ->
                    Log.e("test","funciona")
                    val intent = Intent(this@SimulationFragment.requireContext(),FragmentActivity::class.java)
                    startActivity(intent)

                }
                .show()

        }

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        @JvmStatic
        fun newInstance() = SimulationFragment()
    }
}