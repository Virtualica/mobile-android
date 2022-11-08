package com.virtualica.mobile_android.models.classes.handlers

import com.virtualica.mobile_android.models.classes.Question
import com.virtualica.mobile_android.models.classes.Student

interface DifficultyHandler {
    var nextHandler: DifficultyHandler?
    fun setDifficulty(question: Question, student: Student)

}