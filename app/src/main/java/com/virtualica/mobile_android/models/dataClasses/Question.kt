package com.virtualica.mobile_android.models.dataClasses

import java.io.Serializable

data class Question(
    var id: String = "",
    var categoria: String = "",
    var correcta: String = "",
    var dificultad: String = "",
    var enunciado: String = "",
    var foto: String = "",
    var institucion: String = "",
    var retroalimentacion: String = "",
    var tema: String = "",
    var A : String = "",
    var B : String = "",
    var C : String = "",
    var D : String = "",
    ): Serializable{
  constructor() : this("", "", "", "", "", "", "", "", "", "","","","")
}

