package com.virtualica.mobile_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.register_container.*

class RegisterView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_container);

        val institutions = arrayOf("ICESI", "Javeriana", "Helmer Pardo", "Gabriel Suarez");

        val adapterComplete = ArrayAdapter(this, R.layout.dropdown_institution, institutions)
        (filledTextField4.editText as? AutoCompleteTextView)?.setAdapter(adapterComplete);

    }
}