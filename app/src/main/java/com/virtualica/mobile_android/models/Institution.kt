package com.virtualica.mobile_android.models

import java.io.Serializable

data class Institution(
    var id:String = "",
    var nickname:String = "",
    var name:String = "",
    var email:String = "",
    var password:String = "",
    var domain:String=""
) : Serializable{
    override fun toString(): String {
        return name
    }
    constructor() : this("", "", "", "", "", "")
}
