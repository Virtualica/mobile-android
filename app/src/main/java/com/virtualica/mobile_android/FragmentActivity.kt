package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.virtualica.mobile_android.databinding.SimulationBinding
import kotlinx.android.synthetic.main.simulation.*

class FragmentActivity : AppCompatActivity() {

    private lateinit var navigator:BottomNavigationView
    private lateinit var profile:ImageView
    private lateinit var logo:ImageView
    private  lateinit var appBar:AppBarLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)
        showFragment(CategoryFragment.newInstance())
        appBar = findViewById(R.id.appbar)


        navigator = findViewById(R.id.navigator)
        showFragment(CategoryFragment.newInstance())
        navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.book) {
                showFragment(CategoryFragment.newInstance())
            } else if (menuItem.itemId == R.id.simulation) {
                //showFragment(SimulationFragment.newInstance())
                showDialogToSimulate(SimulationFragment.newInstance())


               // Log.i("dato:","se muestra cuando carga el simulacro")

            } else if (menuItem.itemId == R.id.shop) {
                //showFragment(newTaskFragment)
            }
            true
        }




        profile = findViewById(R.id.profile)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        logo = findViewById(R.id.logo)
        logo.setOnClickListener {
           // Log.e("importante:","estan pasando cosas xdxd")
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }



    }
    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    fun showDialogToSimulate(fragment: Fragment){
        MaterialAlertDialogBuilder(this)
            .setTitle("Estas a punto iniciar el simulacro, ¿Deseas continuar?")
            .setNegativeButton("No, necesito practicar más"){ _, _ ->
                val intent = Intent(this, FragmentActivity::class.java)
                startActivity(intent)
            }
            .setPositiveButton("¡Si!"){ _ , _ ->
                showFragment(fragment)
                navigator.visibility = View.GONE
                appBar.visibility = View.GONE

            }
            .show()

    }

}