package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Stadistic(
    var id : String = "",
    var idStudent: String = "",
    var mejorRacha: Int = 0,
    var peorRacha: Int = 0,
    var mejorCategoria: String = "",
    var peorCategoria: String = "",

): Serializable {
    constructor() : this("", "",0, 0, "", "")
}
