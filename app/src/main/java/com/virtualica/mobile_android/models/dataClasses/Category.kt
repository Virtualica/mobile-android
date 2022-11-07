package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Category(
    var id : String = "",
    var nombre : String = "",
    var desc : String = "",
    var percentage : String=""
):Serializable{
    override fun toString(): String {
        return nombre
    }
    constructor() : this("", "", "", "")
}


