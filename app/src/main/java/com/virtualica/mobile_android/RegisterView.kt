package com.virtualica.mobile_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.User
import com.virtualica.mobile_android.models.Virtualica
import kotlinx.android.synthetic.main.register_container.*

class RegisterView : AppCompatActivity() {

    private lateinit var vr : Virtualica;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_container);

        vr = intent.extras?.getSerializable("virtualica") as Virtualica

        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, vr.getInstitutions())
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete)

        btnGoLogin.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser(){
        if (email_input.text.toString().isNotEmpty() && password_input.text.toString().isNotEmpty()
            && name_input.text.toString().isNotEmpty() && userName_input.text.toString().isNotEmpty()
            && autoCompleteInstitution.text.toString().isNotEmpty() && phone_input.text.toString().isNotEmpty()
            && age_input.text.toString().isNotEmpty()){
            if(checkBox.isChecked){
                if(vr.validateInstitution(autoCompleteInstitution.text.toString())){
                    onRegisterWithAuth()
                } else {
                    Toast.makeText(this,"Por favor ingresa una instiuticón valida, en caso de" +
                            "no pertencer a alguna selecciona 'Sin institución'",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Por favor acepta los terminos y condiciones",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Por favor llena todos los campos",Toast.LENGTH_SHORT).show()
        }
    }


    private fun onRegisterWithAuth() {
        Firebase.auth.createUserWithEmailAndPassword(
            email_input.text.toString(),
            password_input.text.toString()
        ).addOnSuccessListener {
            val user = User(
                Firebase.auth.currentUser?.uid.toString(),
                name_input.text.toString(),
                email_input.text.toString(),
                autoCompleteInstitution.text.toString(),
                phone_input.text.toString(),
                age_input.text.toString(),
                "false"
            )

            if (vr.validateStudentInInstitution(user.email, user.institution)){
                user.isPremiumStudent = true.toString()
                Firebase.firestore.collection("users").document(user.id).set(user).addOnSuccessListener {
                    vr.addUserToList(user)
                    sendVerificationViaEmail()
                }
            } else {
                Log.e("Error", "Ahí un problema sapa")
                Toast.makeText(this, "No perteneces a esta institución", Toast.LENGTH_SHORT).show();
            }
        }.addOnFailureListener{
            showErrorInEmailAndPassword(it.message.toString())
        }
    }

    private fun showErrorInEmailAndPassword(msg : String){
        if(msg.contains("email")){
            Toast.makeText(this, "Por favor ingresa un correo valido", Toast.LENGTH_SHORT).show()
            email_input.setText("")
        } else {
            Toast.makeText(this, "Por favor ingresa una contraseña valida (al menos 6 caracteres)", Toast.LENGTH_SHORT).show()
            password_input.setText("")
        }
    }

    private fun sendVerificationViaEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this,"Se ha enviado un correo de verificacion",Toast.LENGTH_SHORT)
            goLoginActivity()
        }?.addOnFailureListener{
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT)
        }
    }

    private fun goLoginActivity(){
        val intent = Intent(this, LoginView::class.java).apply {
            putExtra("virtualica", vr)
        }
        startActivity(intent)
        finish()
    }
}