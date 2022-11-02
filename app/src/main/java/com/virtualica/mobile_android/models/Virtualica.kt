package com.virtualica.mobile_android.models

class Virtualica() {

    private val users : ArrayList<User>;
    private val institutions: ArrayList<Institution>;

    init {
        users = emptyList<User>() as ArrayList<User>
        institutions = emptyList<Institution>() as ArrayList<Institution>
    }

    public fun addUserToList (user : User){
        users.add(user)
    }

    public fun addInstitutionToList (institution: Institution){
        institutions.add(institution)
    }


}