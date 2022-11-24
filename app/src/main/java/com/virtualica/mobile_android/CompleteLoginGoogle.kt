package com.virtualica.mobile_android

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.virtualica.mobile_android.models.Virtualica
import kotlinx.android.synthetic.main.activity_complete_login_google.*
import kotlinx.android.synthetic.main.activity_complete_login_google.btnLoginComplete
import kotlinx.android.synthetic.main.activity_complete_login_google.filledTextField4
import kotlinx.android.synthetic.main.register_container.*
import java.text.SimpleDateFormat
import java.util.*

class CompleteLoginGoogle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_login_google)

        val vr = intent.extras?.getSerializable("virtualica") as Virtualica
        val uid = intent.extras?.getSerializable("uid") as String
        val givenName = intent.extras?.getSerializable("givenName") as String
        val email = intent.extras?.getSerializable("email") as String


        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, vr.getInstitutions())
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete)

        setCalendar()




        btnLoginComplete.setOnClickListener {
            Log.e("Error", vr.toString())
            Log.e("Error", uid.toString())
            Log.e("Error", givenName.toString())
            Log.e("Error", email.toString())

        }

    }

    private fun loginData(){

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
}