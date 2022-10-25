package com.virtualica.mobile_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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




        btnRegister.setOnClickListener(::Register)


    }

    fun Register(view: View) {
        Firebase.auth.createUserWithEmailAndPassword(
            email_input.text.toString(),
            password_input.text.toString()
        )

    }


}