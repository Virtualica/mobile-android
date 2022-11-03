package com.virtualica.mobile_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.virtualica.mobile_android.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.virtualica.mobile_android.models.User
import kotlinx.android.synthetic.main.register_container.*
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.virtualica.mobile_android.models.Institution
import com.virtualica.mobile_android.models.Virtualica
import java.io.Serializable
import kotlin.math.log

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
        db.collection("institutions").get().addOnSuccessListener{ res ->
            for (document in res){
                val newInstitution = document.toObject(Institution::class.java).also {
                    it.id = document.id
                }
                virtualica.addInstitutionToList(newInstitution);
            }
            goMainActivity()
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