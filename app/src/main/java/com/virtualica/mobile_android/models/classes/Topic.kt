package com.virtualica.mobile_android.models.classes

class Topic {
    private var code: String = ""
    private var name: String = ""
    private var category:Category? = null
     constructor()
    constructor(code:String, name:String) {
            this.code = code
            this.name = name
    }

    fun getcode(): String {
        return code
    }
    fun setCode(code: String) {
        this.code = code
    }
    fun getname(): String {
        return name
    }
    fun setName(name: String) {
        this.name = name
    }

    fun setCategory(category:Category){
        this.category = category
    }

    fun getCategory():Category?{
        return category
    }
}