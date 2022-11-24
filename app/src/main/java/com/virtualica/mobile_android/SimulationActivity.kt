package com.virtualica.mobile_android

import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Question
import kotlinx.android.synthetic.main.activity_simulation.*
import kotlin.random.Random
import kotlin.random.nextInt

class SimulationActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        time()
        showFragment()

    }

    private fun showFragment() {
        val db = Firebase.firestore
        val fragment = QuestionFragment()
        val bundle = Bundle()
        var count = 0
        val questions : MutableList<Question> = ArrayList()

        db.collection("preguntas").get().addOnSuccessListener {  res ->
            for(q in res){
                val newQ = q.toObject(Question::class.java).also {
                    it.id = q.id
                }
                questions.add(newQ)
            }
            val data = putDataQuestion(questions)
            for (q in data){
                bundle.putSerializable("question${count}", q)
                count++
            }
            fragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.questionContainer, fragment)
            transaction.commit()
        }
    }

    private fun putDataQuestion(questions : MutableList<Question>) : MutableList<Question>{
        var questionsToPut : MutableList<Question> = ArrayList()
        val categories = arrayOf("Matemáticas", "Inglés", "Ciencias Sociales", "Lectura Crítica", "Ciencias Naturales")
        for (c in categories){
            val newQuestions = putDataQuestionCategory(c, questions)
            for (nq in newQuestions){
                questionsToPut.add(nq)
            }
        }
        return questionsToPut
    }

    private fun putDataQuestionCategory(category : String, questions : MutableList<Question>) : MutableList<Question>{
        val questionsPerCategory : MutableList<Question> = ArrayList()
        val questionsPerCategoryToPut : MutableList<Question> = ArrayList()
        for (q in questions){
            if(q.categoria == category){
                questionsPerCategory.add(q)
            }
        }
        val setInt = getIndex(questionsPerCategory.size)
        for (i in setInt){
            questionsPerCategoryToPut.add(questionsPerCategory[i])
        }
        return questionsPerCategoryToPut
    }

    private fun getIndex(size : Int) : MutableSet<Int>{
        val indexToQuestion : MutableSet<Int> = mutableSetOf()
        val random = Random
        if(size <= 20){
            while (indexToQuestion.size != size){
                indexToQuestion.add(random.nextInt(0 until size))
            }
        } else {
            while (indexToQuestion.size != 20){
                indexToQuestion.add(random.nextInt(0..size))
            }
        }
        return indexToQuestion
    }


    private fun time(){
        val timeInitial = (1200000).toLong()
        object : CountDownTimer(timeInitial, 1000){

            override fun onFinish() {
                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                val r = RingtoneManager.getRingtone(this@SimulationActivity, notification)
                r.play()
                timeFinished("El tiempo se ha acabado", "Se ha acabado el tiempo para responder las preguntas", r)
            }

            override fun onTick(p0: Long) {
                val timeRes=(p0/1000).toInt()+1
                val minutes = (timeRes/60).toInt()
                val secondRes = timeRes - (minutes*60)
                txtTime.text="Tiempo restante: ${minutes}:${secondRes} minutos"
            }
        }.start()
    }

    private fun timeFinished(msg:String, des:String, r : Ringtone){
        MaterialAlertDialogBuilder(this)
            .setTitle(msg)
            .setMessage(des)
            .setPositiveButton("OK") { dialog, which ->
                r.stop()
                finish()
            }
            .show()
    }
}