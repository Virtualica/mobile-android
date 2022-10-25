package com.virtualica.mobile_android.models

import java.io.Serializable

data class User(
    var id:String = "",
    var name:String = "",
    var email:String = "",
    var institution:String = "",
    var phone: String = "",
    var age: String = "",
    var isPremiumStudent: Boolean = false
) : Serializable{
    override fun toString(): String {
        return name
    }
}
