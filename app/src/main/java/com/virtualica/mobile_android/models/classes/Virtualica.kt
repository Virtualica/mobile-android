package com.virtualica.mobile_android.models.classes

import com.virtualica.mobile_android.models.dataClasses.Institution
import com.virtualica.mobile_android.models.dataClasses.User
import java.io.Serializable


class Virtualica() : Serializable {

    private var users : MutableList<User> = ArrayList()
    private var institutions: MutableList<Institution> = ArrayList()



    fun addUserToList (user : User){
        users.add(user)


    }

    fun addInstitutionToList (institution: Institution){
        institutions.add(institution)
    }

    fun getUser() : MutableList<User>{
        return users
    }

    fun getInstitutions() : MutableList<Institution> {
        return institutions
    }


}