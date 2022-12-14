package com.virtualica.mobile_android


import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Question
import kotlinx.android.synthetic.main.activity_simulation.*
import java.io.FileOutputStream
import kotlin.random.Random



class SimulationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        time()
        showFragment()
        back_simulation.setOnClickListener(){
            MaterialAlertDialogBuilder(this)
                .setTitle("Nunca es tarde para seguir estudiando, ¿Deseas salir?")
                .setNegativeButton("No ¡Sigamos intentando!"){ _, _ ->

                }
                .setPositiveButton("¡Si!"){ _ , _ ->
                    Log.e("test","funciona")
                    val intent = Intent(this,FragmentActivity::class.java)
                    startActivity(intent)

                }
                .show()

        }


        back_simulation.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle("Si sales, no se calcularan tus estadisticas, estas seguro?")
                .setNegativeButton("¡Sigamos entrenando!") { _, _ ->

                }
                .setPositiveButton("¡Si!") { _, _ ->
                    onBackPressed()
                }
                .show()
        }



    }

    private fun showFragment() {
        val fileOutputStream : FileOutputStream
        val db = Firebase.firestore
        val fragment = QuestionFragment()
        val bundle = Bundle()
        var count = 0
        val questions : MutableList<Question> = ArrayList()
        progressBar8.visibility = View.VISIBLE
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
            progressBar8.visibility = View.INVISIBLE

        }
    }



    private fun putDataQuestion(questions : MutableList<Question>) : MutableList<Question>{
        val questionsToPut : MutableList<Question> = ArrayList()
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
        if(size <= 20){
            while (indexToQuestion.size != size){
                val random = Random
                val test = random.nextInt(0, size)
                indexToQuestion.add(test)
            }
        } else {
            while (indexToQuestion.size != 20){
                val random = Random
                indexToQuestion.add(random.nextInt(0, size))
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