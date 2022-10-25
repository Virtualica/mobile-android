package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.practice.*

class TrainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practice)

        back_practice.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Nunca se practica lo suficiente ¿Seguro que deseas salir?")
                .setNegativeButton("¡Sigamos entrenando!"){ _, _ ->

                }
                .setPositiveButton("¡Si!"){ _ , _ ->
                    //Log.e("test","funciona")
                    val intent = Intent(this,FragmentActivity::class.java)
                    startActivity(intent)


                }
                .show()


        }

    }


}