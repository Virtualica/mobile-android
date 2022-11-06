package com.virtualica.mobile_android.models

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
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

    fun validateInstitution(msg : String) : Boolean {
        for (ins in institutions){
            if(msg == ins.nombre){
                return true
            }
        }
        return false
    }

    fun validateStudentInInstitution(email : String, institutionName : String) : Boolean{
        if(institutionName == "Sin institución"){
            return true
        } else {
            for (i in institutions){
                if( i.nombre == institutionName){
                    if(i.estudiantes.contains("[$email]")){
                        return true
                    }
                }
            }
        }
        return false
    }
}