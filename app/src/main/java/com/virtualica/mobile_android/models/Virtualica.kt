package com.virtualica.mobile_android.models

import android.os.Parcel
import android.os.Parcelable
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
            for (ins in institutions){
                if(ins.nombre == institutionName){
                    if(ins.estudiantes.contains("[$email]")){
                        return true
                    }
                }
            }
        }
        return false
    }
}