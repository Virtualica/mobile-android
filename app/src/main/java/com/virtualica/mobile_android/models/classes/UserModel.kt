package com.virtualica.mobile_android.models.classes

open class UserModel(id:Int,nickname:String,name:String,email:String,password:String): UserValidation {
    private var id:Int = id
    private var nickname:String = nickname
    private var name:String = name
    private var email:String = email
    private var password:String = password


    override fun verificateEmail(): Boolean {
        TODO("Not yet implemented")
    }

    override fun verificateUser(): Boolean {
        TODO("Not yet implemented")
    }





}