package com.virtualica.mobile_android.models


class Virtualica() {

    private val users : MutableList<User> = ArrayList()
    private val institutions: MutableList<Institution> = ArrayList()


    public fun addUserToList (user : User){
        users.add(user)
    }

    public fun addInstitutionToList (institution: Institution){
        institutions.add(institution)
    }

    public fun getUser() : MutableList<User>{
        return users
    }

    public fun getInstitutions() : MutableList<Institution> {
        return institutions
    }




}