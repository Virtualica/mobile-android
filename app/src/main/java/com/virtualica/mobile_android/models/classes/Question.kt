package com.virtualica.mobile_android.models.classes

class Question(id:String,statement:String,options:MutableList<String>,correctAnswer:String,feedback:String,private var owner:InstitutionModel) {
    private var category: String = ""
    private var topic: Topic = Topic()


    fun getcategory(): String {
        return category
    }

    fun getOwner(): InstitutionModel {
        return owner
    }

    fun setcategory(category: String) {
        this.category = category
    }

    fun gettopic(): Topic {
        return topic
    }

    fun setTopic(code:String, name:String) {
        this.topic = Topic(code,name)
    }

}



