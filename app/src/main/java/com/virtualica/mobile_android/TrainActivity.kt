package com.virtualica.mobile_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.Virtualica
import com.virtualica.mobile_android.models.dataClasses.Question
import com.virtualica.mobile_android.models.dataClasses.Themes
import kotlinx.android.synthetic.main.practice.*

class TrainActivity : AppCompatActivity() {

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practice)
        showFragment()




        back_practice.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Nunca se practica lo suficiente ¿Seguro que deseas salir?")
                .setNegativeButton("¡Sigamos entrenando!") { _, _ ->

                }
                .setPositiveButton("¡Si!") { _, _ ->
                    val intent = Intent(this, FragmentActivity::class.java)
                    startActivity(intent)

                }
                .show()
        }
    }

    private fun showFragment() {
        val topic = intent.extras?.getString("topic")
        val fragment = QuestionFragment()
        val bundle = Bundle()

        var count = 0
        progressBar7.visibility = View.VISIBLE
        db.collection("preguntas").whereEqualTo("tema", topic).get().addOnSuccessListener { res ->
            for (q in res) {
                val newQ = q.toObject(Question::class.java).also {
                    it.id = q.id
                }
                bundle.putSerializable("question$count", newQ)
                count++
            }
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.questionContainer, fragment)
            transaction.commit()
            progressBar7.visibility = View.INVISIBLE
        }
    }
}