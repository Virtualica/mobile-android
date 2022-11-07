package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Institution(
    var id:String = "",
    var nickname:String = "",
    var nombre:String = "",
    var correo:String = "",
    var contrasena:String = "",
    var dominio:String="",
    var student:String=""
) : Serializable{
    override fun toString(): String {
        return nombre
    }
    constructor() : this("", "", "", "", "", "", "")
}
