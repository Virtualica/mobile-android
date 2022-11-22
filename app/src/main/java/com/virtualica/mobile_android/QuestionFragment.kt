package com.virtualica.mobile_android

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
    private var out = false



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var res : String? = null

        val inf = inflater.inflate(R.layout.question, container, false)
        val dataQuestion = arguments
        for (i in 0 until dataQuestion!!.size()){
            val q : Question = dataQuestion.get("question${i}") as Question
            questions.add(q)
        }
        changeData(inf)

        inf.next.setOnClickListener {

            if(!out){
                if(res != null) {
                    if(res != questions[pos].correcta){
                        super.getContext()?.let { it1 ->
                            Dialog("Respuesta Incorrecta", "Has fallado, ${questions[pos].retroalimentacion}",
                                it1
                            )
                        }
                    } else {
                        super.getContext()?.let { it1 ->
                            Dialog("Respuesta correcta", "Felicidades, has acertado",
                                it1
                            )
                        }
                    }
                    pos++
                    changeData(inf)
                } else {
                    super.getContext()?.let { it1 ->
                        Dialog("Sin respuesta", "Por favor, responde la pregunta para continuar",
                            it1
                        )
                    }
                }
                resetButtons(inf)
            }


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

            if(pos < questions.size){
                inf.countQuestion.text = "Pregunta " + (pos+1).toString()
                inf.questionContent.text = questions[pos].enunciado
                inf.opA.text = "A. ${questions[pos].A}"
                inf.opB.text = "B. ${questions[pos].B}"
                inf.opC.text = "C. ${questions[pos].C}"
                inf.opD.text = "D. ${questions[pos].D}"
            } else {
                out = true
                super.getContext()?.let { it1 ->
                    Dialog("Has terminado", "Felcidades, has terminado todas las preguntas",
                        it1
                    )
                }
                val intent = Intent (super.getContext(), FragmentActivity::class.java)
                startActivity(intent)
            }





    }


    private fun Dialog(msg : String, des : String, ct : Context){
        MaterialAlertDialogBuilder(ct)
            .setTitle(msg)
            .setMessage(des)
            .setPositiveButton("OK") { dialog, which ->
            }
            .show()
    }
}