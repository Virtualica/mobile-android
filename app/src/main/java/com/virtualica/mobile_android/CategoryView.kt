package com.virtualica.mobile_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.android.synthetic.main.register_container.*

class CategoryView : AppCompatActivity() {

    private lateinit var elementsList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_item_list)

        val categories = arrayOf("Matematicas", "Ciencias Naturales", "Ciencias Sociales", "Lenguaje", "Ingles")
        val adapterCategory = ArrayAdapter(this, R.layout.fragment_item, categories)
        (listInCategory as? ListView)?.adapter = adapterCategory;

        elementsList = findViewById(R.id.listInCategory)
    }


}


