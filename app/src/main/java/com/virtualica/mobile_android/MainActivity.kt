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

class  MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {

        //re.layoutManager = GridLayoutManager(applicationContext,2)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")

        /*
        if(json != "NO_USER"){
            goMainActivity()
        }

         */


        btnCreateAccount.setOnClickListener(){
            val intent = Intent(this, RegisterView::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(){
            login(internalMemory)
        }

        btnLoginGoogle.setOnClickListener(){
            loginGoogle()
        }


    }

    private fun login(iMemory : SharedPreferences){
        val username = loginEmail.text.toString();
        val password = loginPassword.text.toString();

        if(username.isNotEmpty() && password.isNotEmpty()){


            Firebase.auth.signInWithEmailAndPassword(username, password).addOnSuccessListener {
                val fbUserCurr = Firebase.auth.currentUser

                if(fbUserCurr!!.isEmailVerified){
                    Firebase.firestore.collection("users").document(fbUserCurr.uid).get().addOnSuccessListener {
                        val userActive = it.toObject(User::class.java)
                        keepSessionStarted(userActive!!, iMemory)
                        goMainActivity()
                    }

                } else {
                    Toast.makeText(this, "Por favor, verfica tu cuenta", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Ingresa todo los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private val resultLauncher = registerForActivityResult(StartActivityForResult()){res ->
        println("HOla, aqui va el res: " + res.toString())

    }


    private fun loginGoogle(){
        val userGoogleConfiguration = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val userGoogleActive = GoogleSignIn.getClient(this, userGoogleConfiguration)
        resultLauncher.launch(userGoogleActive.signInIntent)
    }

    private fun goMainActivity(){
        startActivity(Intent(this, FragmentActivity::class.java))
    }

    private fun keepSessionStarted(user : User, iMemory: SharedPreferences){
        val json = Gson().toJson(user)
        iMemory.edit().putString("users", json).apply()
    }
}