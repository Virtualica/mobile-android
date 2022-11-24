package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.Virtualica
import com.virtualica.mobile_android.models.dataClasses.Category
import com.virtualica.mobile_android.models.dataClasses.Question

class FragmentActivity : AppCompatActivity() {

    private lateinit var navigator:BottomNavigationView
    private lateinit var profile:ImageView
    private lateinit var logo:ImageView
    private  lateinit var appBar:AppBarLayout
    private lateinit var vr : Virtualica;
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)
        showFragment()
        appBar = findViewById(R.id.appbar)

        vr = intent.extras?.getSerializable("virtualica") as Virtualica

        navigator = findViewById(R.id.navigator)
        navigator.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.book -> {
                    showFragment()
                }
                R.id.simulation -> {
                    showDialogToSimulate()
                }
            }
            true
        }




        profile = findViewById(R.id.profile)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java).apply { putExtra("virtualica", vr) }
            startActivity(intent)
        }

        logo = findViewById(R.id.logo)
        logo.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }



    }
    private fun showFragment() {
        val fragment = CategoryFragment()
        val bundle = Bundle()
        var count = 0
        db.collection("categorias").get().addOnSuccessListener { res ->
            for (c in res){
                val newC = c.toObject(Category::class.java).also {
                    it.id = c.id
                }
                bundle.putSerializable("category${count}", newC)
                count++
            }
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            transaction.commit()
        }
    }

    private fun showDialogToSimulate(){
        MaterialAlertDialogBuilder(this)
            .setTitle("Estas a punto iniciar el simulacro, ¿Deseas continuar?")
            .setNegativeButton("No, necesito practicar más"){ _, _ ->
                val intent = Intent(this, FragmentActivity::class.java)
                startActivity(intent)
            }
            .setPositiveButton("¡Si!"){ _ , _ ->
                val intent = Intent(this, SimulationActivity::class.java)
                startActivity(intent)
            }
            .show()
    }
}