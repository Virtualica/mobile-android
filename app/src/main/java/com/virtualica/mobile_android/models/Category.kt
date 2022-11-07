package com.virtualica.mobile_android.models

import java.io.Serializable

data class Category(
    var id : String = "",
    var nombre : String = "",
    var desc : String = "",
    var percentage : Number = 0
):Serializable{
    override fun toString(): String {
        return nombre
    }
    constructor() : this("", "", "", 0)
}


