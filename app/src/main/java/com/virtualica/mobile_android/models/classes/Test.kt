package com.virtualica.mobile_android.models.classes

open class Test(private var topic: String) {

    fun getTopic(): String {
        return topic
    }

    fun setTopic(topic: String) {
        this.topic = topic
    }

    open fun getGrade(): Int {
        //TODO
        return 0
    }

    fun nextQuestion(current:Question): Question {
        //TODO
        return current
    }

    fun verifyAnswer(answer: String, idQuestion: String): Boolean {
        //TODO
        return false
    }
}