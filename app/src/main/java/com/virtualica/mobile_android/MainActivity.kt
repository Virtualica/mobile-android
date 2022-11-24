package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Institution
import com.virtualica.mobile_android.models.Virtualica

class  MainActivity : AppCompatActivity() {

    private lateinit var virtualica : Virtualica
    private var db  = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        virtualica = Virtualica()
        getInstitutions()
    }

    private fun getInstitutions(){
        var count = 0
        db.collection("institutions").get().addOnSuccessListener{ res ->
            for (document in res){
                document.toObject(Institution::class.java).also {
                    it.id = document.id
                    db.collection("institutions").document(document.id).collection("estudiantes").get().addOnSuccessListener { estRes ->
                        for (i in estRes){
                            val emailString = i.id
                            it.estudiantes.add(emailString)
                        }
                        virtualica.addInstitutionToList(it)
                        if(count == res.size()-1){
                            goMainActivity()
                            finish()
                        }
                        count++
                    }
                }
            }
        }

    }

    private fun goMainActivity(){
        val intent = Intent(this, LoginView::class.java).apply {
            putExtra("virtualica", virtualica)
        }
        startActivity(intent)
        finish();
    }
}