package com.virtualica.mobile_android.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


class Virtualica() : Serializable {

    private var users : MutableList<User> = ArrayList()
    private var institutions: MutableList<Institution> = ArrayList()


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