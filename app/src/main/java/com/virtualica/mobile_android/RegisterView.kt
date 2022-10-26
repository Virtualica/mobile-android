package com.virtualica.mobile_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.User
import kotlinx.android.synthetic.main.register_container.*

class RegisterView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_container);

        val institutions = arrayOf("ICESI", "Javeriana", "Helmer Pardo", "Gabriel Suarez");

        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, institutions)
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete);

        btnGoLogin.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




        btnRegister.setOnClickListener{
            if (email_input.text.toString().isNotEmpty() && password_input.text.toString().isNotEmpty()
                && name_input.text.toString().isNotEmpty() && userName_input.text.toString().isNotEmpty()
                && autoCompleteInstitution.text.toString().isNotEmpty() && phone_input.text.toString().isNotEmpty()
                && age_input.text.toString().isNotEmpty()
            ){
                if(checkBox.isChecked){
                    Register()
                }else{
                    Toast.makeText(this,"Por favor acepta los terminos y condiciones",Toast.LENGTH_SHORT)
                }
            }else{
                Toast.makeText(this,"Por favor llena todos los campos",Toast.LENGTH_SHORT)
            }
        }


    }


    fun Register() {
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

                Firebase.firestore.collection("users").document(user.id).set(user).addOnSuccessListener {
                        sendVerificationViaEmail()
                        finish()

                }



        }




    }

    fun sendVerificationViaEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this,"Se ha envidado un correo de verificacion",Toast.LENGTH_SHORT)
        }?.addOnFailureListener{
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT)
        }
    }


}