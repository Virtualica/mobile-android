package com.virtualica.mobile_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Category
import com.virtualica.mobile_android.models.dataClasses.Question
import kotlinx.android.synthetic.main.question.*
import kotlinx.android.synthetic.main.question.view.*


class QuestionFragment : Fragment() {


    private val questions : MutableList<Question> = ArrayList()
    private var pos = 0;


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
            pos++
            changeData(inf)
        }

        return inf
    }


    private fun changeData(inf : View){
        inf.countQuestion.text = "Pregunta " + (pos+1).toString()
        inf.questionContent.text = questions[pos].enunciado
        inf.opA.text = questions[pos].A
        inf.opB.text = questions[pos].B
        inf.opC.text = questions[pos].C
        inf.opD.text = questions[pos].D
    }



    companion object{
        @JvmStatic
        fun newInstance() = QuestionFragment()
    }
}