package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Stadistic(
    var id : String = "",
    var idStudent: String = "",
    var mejorCategoria: String = "",
    var mejorRacha: Int = 0,
    var peorCategoria: String = "",
    var ultimoSimulacrio:String = "",

): Serializable {
    constructor() : this("", "",  "",0, "","")
}
