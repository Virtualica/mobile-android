package com.virtualica.mobile_android

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.virtualica.mobile_android.models.dataClasses.Question
import kotlinx.android.synthetic.main.question.view.*


class QuestionFragment() : Fragment() {


    private val questions : MutableList<Question> = ArrayList()
    private var pos = 0
    var res : String? = null
    private var correct = 0
    var category:String? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inf = inflater.inflate(R.layout.question, container, false)
        val dataQuestion = arguments
        for (i in 0 until dataQuestion!!.size()){
            val q : Question = dataQuestion.get("question${i}") as Question
            questions.add(q)
        }

        changeData(inf)
        inf.next.setOnClickListener {
            if(res != null){
                if(res == questions[pos].correcta){
                    correct++
                    category = questions[pos].categoria
                    dialogue("Respuesta correcta", "Felcidades, has acertado", true, inf)
                } else {
                    dialogue("Respuesta incorrecta", "Lo sentimos, has fallado ${questions[pos].retroalimentacion}", true, inf)
                }
                pos++
            } else {
                dialogue("Selecciona una respuesta", "Por favor, selecciona una respuesta", false, inf)
            }
            resetButtons(inf)
        }

        inf.opA.setOnClickListener {
            resetButtons(inf)
            inf.opA.setBackgroundColor(Color.parseColor("#92106d"))
            inf.opA.setTextColor(Color.parseColor("#FFFFFFFF"))
            res = "A"
        }
        inf.opB.setOnClickListener {
            resetButtons(inf)
            inf.opB.setBackgroundColor(Color.parseColor("#92106d"))
            inf.opB.setTextColor(Color.parseColor("#FFFFFFFF"))
            res = "B"
        }
        inf.opC.setOnClickListener {
            resetButtons(inf)
            inf.opC.setBackgroundColor(Color.parseColor("#92106d"))
            inf.opC.setTextColor(Color.parseColor("#FFFFFFFF"))
            res = "C"
        }
        inf.opD.setOnClickListener {
            resetButtons(inf)
            inf.opD.setBackgroundColor(Color.parseColor("#92106d"))
            inf.opD.setTextColor(Color.parseColor("#FFFFFFFF"))
            res = "D"
        }
        return inf
    }

    private fun resetButtons(inf:View){
        inf.opA.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        inf.opB.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        inf.opC.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        inf.opD.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        inf.opA.setTextColor(Color.parseColor("#92106d"))
        inf.opB.setTextColor(Color.parseColor("#92106d"))
        inf.opC.setTextColor(Color.parseColor("#92106d"))
        inf.opD.setTextColor(Color.parseColor("#92106d"))
    }


    private fun changeData(inf : View){
        inf.countQuestion.text = "Pregunta " + (pos+1).toString()
        inf.questionContent.text = questions[pos].enunciado
        inf.opA.text = "A. ${questions[pos].A}"
        inf.opB.text = "B. ${questions[pos].B}"
        inf.opC.text = "C. ${questions[pos].C}"
        inf.opD.text = "D. ${questions[pos].D}"
    }


    private fun dialogue(msg : String, des : String, out : Boolean, inf: View) {
        super.getContext()?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(msg)
                .setMessage(des)
                .setPositiveButton("OK") { dialog, which ->
                    if(out){
                        if(pos < questions.size){
                            changeData(inf)
                            res=null

                        } else {
                            dialogue("Has terminado", "Felicidades, has completado la prueba", inf)
                        }

                    }
                }
                .show()
        }
    }

    private fun dialogue(msg : String, des : String, inf: View) {
        super.getContext()?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(msg)
                .setMessage(des)
                .setPositiveButton("OK") { dialog, which ->
                    val intent = Intent (super.getContext(), FragmentActivity::class.java)
                    intent.putExtra("correct", correct)
                    intent.putExtra("category", category)
                    startActivity(intent)
                }
                .show()

        }
    }
}