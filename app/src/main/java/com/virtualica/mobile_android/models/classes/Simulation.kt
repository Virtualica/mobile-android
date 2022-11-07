package com.virtualica.mobile_android.models.classes

class Simulation(topic: String): Test(topic) {

    private var code:String = ""
    private var score:Int = 0
    private var time:Int = 0
    private var questions: List<Question?> = listOf(*arrayOfNulls<Question>(100))

    constructor(code:String,topic: String): this(topic) {
        this.code = code
    }
    override fun getGrade(): Int {
        return score
    }

    fun setScore(score:Int) {
        this.score = score
    }
    fun getSocre(): Int {
        return score
    }

    fun setTime(time:Int) {
        this.time = time
    }

    fun getTime(): Int {
        return time
    }

    fun setQuestions(questions:List<Question?>) {
        this.questions = questions
    }
    fun getQuestions(): List<Question?> {
        return questions
    }

    fun addQuestion(question:Question) {
        questions.plus(question)
    }

    fun setCode(code:String) {
        this.code = code
    }
    fun getCode(): String {
        return code
    }




}