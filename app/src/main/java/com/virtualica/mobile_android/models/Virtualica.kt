package com.virtualica.mobile_android.models


import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.virtualica.mobile_android.models.dataClasses.Institution
import com.virtualica.mobile_android.models.dataClasses.Stadistic
import com.virtualica.mobile_android.models.dataClasses.User
import java.io.Serializable


class Virtualica():Serializable  {

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
                    if(i.estudiantes.contains(email)){
                        return true
                    }
                }
            }
        }
        return false
    }

    fun caluclateStadistics(id:String,goodAnswered:Int,category:String,lastSimulation:String){
        val db = Firebase.firestore
        var stadistic = Stadistic()
        db.collection("estadisticas").whereEqualTo("idStudent",id).get().addOnSuccessListener { res ->
            if(res.isEmpty){
                //add a ramdom uid for id field
                stadistic.idStudent = id
                stadistic.mejorRacha = goodAnswered
                stadistic.mejorCategoria = category
                stadistic.peorCategoria = category
                stadistic.ultimoSimulacrio = lastSimulation
                db.collection("estadisticas").add(stadistic)
            }else{
                for (doc in res){
                    Log.e("doc: ",doc.id)
                    stadistic = doc.toObject(Stadistic::class.java)
                    if(goodAnswered > stadistic.mejorRacha){
                        stadistic.mejorRacha = goodAnswered
                        stadistic.mejorCategoria = category
                    }

                    if (lastSimulation != ""){
                        stadistic.ultimoSimulacrio = lastSimulation
                    }
                    db.collection("estadisticas").document(doc.id).set(stadistic)
                }
            }
        }

    }

}
