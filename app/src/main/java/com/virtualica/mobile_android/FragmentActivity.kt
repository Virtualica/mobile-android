package com.virtualica.mobile_android

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.virtualica.mobile_android.models.dataClasses.Category
import com.virtualica.mobile_android.models.dataClasses.User
import kotlinx.android.synthetic.main.bottom_bar.*
import java.io.File

class FragmentActivity : AppCompatActivity() {

    private lateinit var navigator:BottomNavigationView
    private lateinit var profile:ImageView
    private lateinit var logo:ImageView
    private val storage = Firebase.storage
    private  lateinit var appBar:AppBarLayout
    private lateinit var user : User
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)
        showFragment()
        appBar = findViewById(R.id.appbar)


        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")
        user = Gson().fromJson(json, User::class.java)
        loadImageProfile()


        navigator = findViewById(R.id.navigator)
        navigator.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.book -> {
                    showFragment()
                }
                R.id.simulation -> {
                    if(validateStudentSimulataion()){
                        showDialogToSimulate()
                    } else {
                        showDialogToNoSimulation()
                    }
                }
            }
            true
        }

        profile = findViewById(R.id.profileAppBar)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }




    }

    private fun loadImageProfile(){
        if(user.foto != ""){
            val localPhotoProfile = Firebase.storage.reference.child("profile_photo/${user.foto}")
            val localFileProfile = File.createTempFile("image", "jpg")
            localPhotoProfile.getFile(localFileProfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFileProfile.absolutePath)
                profileAppBar.setImageBitmap(bitmap)
            }
        }
    }


    private fun showFragment() {
        val fragment = CategoryFragment()
        val bundle = Bundle()
        var count = 0
        progressBar5.visibility = View.VISIBLE

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
            progressBar5.visibility = View.INVISIBLE
        }
    }

    private fun showDialogToSimulate(){
        MaterialAlertDialogBuilder(this)
            .setTitle("Estas a punto iniciar el simulacro, ¿Deseas continuar?")
            .setNegativeButton("No, necesito practicar más"){ _, _ ->
            }
            .setPositiveButton("¡Si!"){ _ , _ ->
                val intent = Intent(this, SimulationActivity::class.java)
                startActivity(intent)
            }
            .show()
    }

    private fun showDialogToNoSimulation(){
        MaterialAlertDialogBuilder(this)
            .setTitle("¿Deseas realizar un simulacro?")
            .setMessage("Asociate a una instiutcion o prueba nuestra versión premium")
            .setPositiveButton("OK"){ _ , _ ->
            }
            .show()
    }

    private fun validateStudentSimulataion() : Boolean{
        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")
        val userActive = Gson().fromJson(json, User::class.java)
        return userActive.isPremiumStudent.toBoolean()
    }
}