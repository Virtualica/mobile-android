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
import com.virtualica.mobile_android.models.Virtualica

class  MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var virtualica : Virtualica = Virtualica()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")


        if(json != "NO_USER"){
            goMainActivity()
        }

        val registerView : RegisterView = RegisterView();
        registerView.getVirtualica(this.virtualica)

        btnCreateAccount.setOnClickListener(){
            val intent = Intent(this, registerView::class.java)
            startActivity(intent)
            finish();
        }


        btnLogin.setOnClickListener(){
            login(internalMemory)
        }

        btnLoginGoogle.setOnClickListener(){
            loginGoogle()
        }

        btnForgetPassword.setOnClickListener(){
            Log.e("Error", virtualica.getInstitutions().size.toString()+"Sapa")
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
        if(res.resultCode == Activity.RESULT_OK){
            val taskUser = GoogleSignIn.getSignedInAccountFromIntent(res.data)
            val accountGoogle = taskUser.result

            if(accountGoogle != null){
                val credentialUser = GoogleAuthProvider.getCredential(accountGoogle.idToken, null)
                Firebase.auth.signInWithCredential(credentialUser).addOnSuccessListener {
                    val user = User(
                        Firebase.auth.currentUser?.uid.toString(),
                        accountGoogle.givenName!!,
                        accountGoogle.email!!,
                        "ICESI",
                        "", "", "False"
                    )
                    Firebase.firestore.collection("users").document(user.id).set(user).addOnSuccessListener {
                        goMainActivity()
                    }
                }
            }
        }
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