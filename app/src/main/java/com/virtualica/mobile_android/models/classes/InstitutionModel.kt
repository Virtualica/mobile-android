package com.virtualica.mobile_android.models.classes

class InstitutionModel(id:Int,nickname:String,name:String,email:String,password:String,domain:String): UserModel(id,nickname,name,email,password) , Provider {
    private var domain: String = domain
    private var students: ArrayList<Student> = ArrayList()
    private var bankQuestions: ArrayList<Question> = ArrayList()


    fun addQuestion(question: Question){
        bankQuestions.add(question)
    }
    fun getBankQuestions(): ArrayList<Question> {
        return bankQuestions
    }

    fun addStudent(student: Student){
        students.add(student)
    }

    fun getSudents(): ArrayList<Student> {
        return students
    }

    fun getDomain(): String {
        return domain
    }

    fun setDomain(domain: String) {
        this.domain = domain
    }

    override fun loadQuestionsData(): Boolean {
        TODO("Not yet implemented")
    }

    fun verifyStudent(student: Student): Boolean {
        return students.contains(student)
    }


}
