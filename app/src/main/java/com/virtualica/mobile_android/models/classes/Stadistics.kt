package com.virtualica.mobile_android.models.classes

class Stadistics {
   private var bestStreak: Int = 0
    private var bestScore: Int = 0
    private var lastScore: Int = 0
   private  var worstCategory: String = ""
    private var bestCategory: String = ""
    //create setters and getters

    fun getBestStreak(): Int {
        return bestStreak
    }
    fun setBestStreak(bestStreak: Int) {
        this.bestStreak = bestStreak
    }
    fun getBestScore(): Int {
        return bestScore
    }
    fun setBestScore(bestScore: Int) {
        this.bestScore = bestScore
    }
    fun getLastScore(): Int {
        return lastScore
    }
    fun setLastScore(lastScore: Int) {
        this.lastScore = lastScore
    }
    fun getWorstCategory(): String {
        return worstCategory
    }
    fun setWorstCategory(worstCategory: String) {
        this.worstCategory = worstCategory
    }
    fun getBestCategory(): String {
        return bestCategory
    }
    fun setBestCategory(bestCategory: String) {
        this.bestCategory = bestCategory
    }



}