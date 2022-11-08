package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Question(
    var id: String = "",
    var categoria: String = "",
    var tema: String = "",
    var foto: String = "",
    var retroalimentacion: String = "",
    var correcta: String = "",
    var enunciado: String = "",
    var opciones: MutableList<String> = ArrayList()
): Serializable{
 constructor() : this("", "", "", "", "", "", "", ArrayList())
}

