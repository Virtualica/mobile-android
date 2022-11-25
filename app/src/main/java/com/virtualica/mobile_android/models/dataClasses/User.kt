package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class User(
    var id:String = "",
    var name:String = "",
    var username:String="",
    var email:String = "",
    var institution:String = "",
    var phone: String = "",
    var age: String = "",
    var isPremiumStudent: String = ""
) : Serializable{
    override fun toString(): String {
        return name
    }
    constructor(): this("","","","","","", "", "")
}

