package com.virtualica.mobile_android

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.virtualica.mobile_android.models.Virtualica
import com.virtualica.mobile_android.models.dataClasses.User
import kotlinx.android.synthetic.main.activity_complete_login_google.*
import kotlinx.android.synthetic.main.activity_complete_login_google.btnLoginComplete
import kotlinx.android.synthetic.main.activity_complete_login_google.filledTextField4
import kotlinx.android.synthetic.main.register_container.*
import java.text.SimpleDateFormat
import java.util.*

class CompleteLoginGoogle : AppCompatActivity() {

    private lateinit var vr : Virtualica
    private lateinit var uid : String
    private lateinit var givenName : String
    private lateinit var email : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_login_google)

        vr = intent.extras?.getSerializable("virtualica") as Virtualica
        uid = intent.extras?.getSerializable("uid") as String
        givenName = intent.extras?.getSerializable("givenName") as String
        email = intent.extras?.getSerializable("email") as String


        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, vr.getInstitutions())
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete)

        setCalendar()

        btnLoginComplete.setOnClickListener {
            loginData()
        }
    }

    private fun loginData(){
        if(userName_inputGoogle.text.toString().isNotEmpty() && autoCompleteInstitutionGoogle.text.toString().isNotEmpty()
            && phone_inputGoogle.text.toString().isNotEmpty()
            && (btnAgeGoogle.text.toString().isNotEmpty() && btnAgeGoogle.text.toString() != "Edad")){
            if(checkBoxGoogle.isChecked){
                if(vr.validateInstitution(autoCompleteInstitutionGoogle.text.toString())){
                    if(autoCompleteInstitutionGoogle.text.toString() == "Sin institución"){
                        onRegisterWithAuth(false)
                    } else {
                        if(vr.validateStudentInInstitution(email, autoCompleteInstitutionGoogle.text.toString())){
                            onRegisterWithAuth(true)
                        } else {
                            Toast.makeText(this,"No perteneces a esta institución",Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this,"Por favor ingresa una instiuticón valida, en caso de" +
                            "no pertencer a alguna selecciona 'Sin institución'",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Por favor acepta los terminos y condiciones",Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onRegisterWithAuth(out: Boolean) {
        val user = User(
            uid,
            givenName,
            userName_inputGoogle.text.toString(),
            email,
            autoCompleteInstitutionGoogle.text.toString(),
            phone_inputGoogle.text.toString(),
            btnAgeGoogle.text.toString(),
            out.toString()
        )
        Firebase.firestore.collection("users").document(user.id).set(user).addOnSuccessListener {
            progressBar4.visibility = View.VISIBLE
            vr.addUserToList(user)
            saveUser(user)
            goMainActivity()
        }

    }

    private fun goMainActivity(){
        startActivity(Intent(this, FragmentActivity::class.java).apply { putExtra("virtualica", vr) })
        finish()
    }

    private fun setCalendar(){
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabelAge(myCalendar)
        }
        btnAgeGoogle.setOnClickListener{
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLabelAge(myCalendar : Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        btnAgeGoogle.text = sdf.format(myCalendar.time)
    }

    private fun saveUser(user : User){
        val internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = Gson().toJson(user)
        internalMemory.edit().putString("users", json).apply()


    }
}