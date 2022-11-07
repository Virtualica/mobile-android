package com.virtualica.mobile_android.models.classes

class Student(phone:String,age:String,premium:Boolean,picture:String,studentDifficulty: Difficulty,id:Int,nickname:String,name:String,email:String,password:String):UserModel(id,nickname,name,email,password) {
    private var studentStadistics: Stadistics = Stadistics()
    private var phone: String = phone
    private var age: String = age
    private var isPremiumStudent: Boolean = premium
    private var picture: String = picture
    private var studentDifficulty: Difficulty = studentDifficulty



    fun getStudentStadistics(): Stadistics {
        return studentStadistics
    }

    //create setters and getters

    fun getPhone(): String {
        return phone
    }
    fun setPhone(phone: String) {
        this.phone = phone
    }
    fun getAge(): String {
        return age
    }
    fun setAge(age: String) {
        this.age = age
    }
    fun isPremiumStudent(): Boolean {
        return isPremiumStudent
    }
    fun setPremiumStudent(premiumStudent: Boolean) {
        isPremiumStudent = premiumStudent
    }
    fun getPicture(): String {
        return picture
    }
    fun setPicture(picture: String) {
        this.picture = picture
    }
    fun getStudentDifficulty(): Difficulty {
        return studentDifficulty
    }
    fun setStudentDifficulty(studentDifficulty: Difficulty) {
        this.studentDifficulty = studentDifficulty
    }




}