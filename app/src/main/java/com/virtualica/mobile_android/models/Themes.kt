package com.virtualica.mobile_android.models

import java.io.Serializable

data class Themes(
    var id : String = "",
    var categoria : String = "",
    var nombre : String = ""
) : Serializable{
    override fun toString(): String {
        return nombre
    }
    constructor() : this ("", "", "")
}