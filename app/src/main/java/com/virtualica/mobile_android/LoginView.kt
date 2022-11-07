package com.virtualica.mobile_android

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.virtualica.mobile_android.models.dataClasses.User
import com.virtualica.mobile_android.models.Virtualica
import kotlinx.android.synthetic.main.login_container.*

class LoginView : AppCompatActivity() {

    private lateinit var vr : Virtualica

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_container)


        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")


        vr = intent.extras?.getSerializable("virtualica") as Virtualica


        if(json != "NO_USER"){
            goMainActivity()
        }


        btnCreateAccount.setOnClickListener(){
            val intent = Intent(this, RegisterView::class.java)
            intent.putExtra("virtualica", vr)
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
        }
    }

    private fun login(iMemory : SharedPreferences){
        val username = loginEmail.text.toString()
        val password = loginPassword.text.toString()

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
                    Toast.makeText(this,"Por favor, verifica tu cuenta",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, "Correo o contraseña incorrectos, intenta de nuevo", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ingresa todo los campos", Toast.LENGTH_SHORT).show()
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

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ res ->
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

    private fun goMainActivity(){
        startActivity(Intent(this, FragmentActivity::class.java))
    }

    private fun keepSessionStarted(user : User, iMemory: SharedPreferences){
        val json = Gson().toJson(user)
        iMemory.edit().putString("users", json).apply()
    }
}

