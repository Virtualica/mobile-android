package com.virtualica.mobile_android.models.classes

class Practice(topic: String):Test(topic) {
    private var amountOfCorrectAnswers: Int = 0
    private var amountOfIncorrectAnswers: Int = 0
    private var questions: List<Question?> = listOf(*arrayOfNulls<Question>(10))

    fun getAmountOfCorrectAnswers(): Int {
        return amountOfCorrectAnswers
    }
    fun setAmountOfCorrectAnswers(amountOfCorrectAnswers: Int) {
        this.amountOfCorrectAnswers = amountOfCorrectAnswers
    }

    fun getAmountOfIncorrectAnswers(): Int {
        return amountOfIncorrectAnswers
    }
    fun setAmountOfIncorrectAnswers(amountOfIncorrectAnswers: Int) {
        this.amountOfIncorrectAnswers = amountOfIncorrectAnswers
    }

    fun getQuestions(): List<Question?> {
        return questions
    }
    fun setQuestions(questions: List<Question?>) {
        this.questions = questions
    }
    fun addQuestion(question:Question) {
        questions.plus(question)
    }

    fun getFeedback(questionID: String): String {
        //TODO
        return ""
    }
}