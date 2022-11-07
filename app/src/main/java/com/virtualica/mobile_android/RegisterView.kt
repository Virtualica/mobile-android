package com.virtualica.mobile_android

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.User
import com.virtualica.mobile_android.models.Virtualica
import kotlinx.android.synthetic.main.register_container.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterView : AppCompatActivity() {

    private lateinit var vr : Virtualica;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_container);

        vr = intent.extras?.getSerializable("virtualica") as Virtualica

        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, vr.getInstitutions())
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete)

        setCalendar()

        btnGoLogin.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener{
            registerUser()
        }
    }

    private fun setCalendar(){
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabelAge(myCalendar)
        }
        btnAge.setOnClickListener{
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


    }

    private fun updateLabelAge(myCalendar : Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        btnAge.text = sdf.format(myCalendar.time)
    }

    private fun registerUser(){
        if (email_input.text.toString().isNotEmpty() && password_input.text.toString().isNotEmpty()
            && name_input.text.toString().isNotEmpty() && userName_input.text.toString().isNotEmpty()
            && autoCompleteInstitution.text.toString().isNotEmpty() && phone_input.text.toString().isNotEmpty()
            && (btnAge.text.toString().isNotEmpty() && btnAge.text.toString() != "Edad")){
            if(checkBox.isChecked){
                if(vr.validateInstitution(autoCompleteInstitution.text.toString())){
                    if (vr.validateStudentInInstitution(email_input.text.toString(), autoCompleteInstitution.text.toString())){
                        onRegisterWithAuth()
                    } else {
                        Toast.makeText(this,"No perteneces a esta institución",Toast.LENGTH_SHORT).show()
                    }

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
                btnAge.text.toString(),
                "false"
            )

            if (vr.validateStudentInInstitution(user.email, user.institution)){
                user.isPremiumStudent = true.toString()
                Firebase.firestore.collection("users").document(user.id).set(user).addOnSuccessListener {
                    vr.addUserToList(user)
                    sendVerificationViaEmail()
                }
            } else {
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