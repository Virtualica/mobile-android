package com.virtualica.mobile_android

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.ktx.storage
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.virtualica.mobile_android.models.Virtualica
import com.virtualica.mobile_android.models.dataClasses.User
import kotlinx.android.synthetic.main.profile.*
import java.io.File
import kotlin.math.log

class ProfileActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val REQUEST_PERMISSION = 100
    private var imageUri: Uri? = null
    private val storage = Firebase.storage
    private val db = Firebase.firestore
    private lateinit var user : User
    private lateinit var internalMemory : SharedPreferences
    private var out = false


    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        internalMemory = getSharedPreferences("smart_insurance", MODE_PRIVATE)
        val json = internalMemory.getString("users", "NO_USER")
        user = Gson().fromJson(json, User::class.java)

        logo.setOnClickListener {
            onBackPressed()
        }

        profile.setOnClickListener {
            checkExternalStoragePermission()
        }
        setFields()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(out){
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setFields() {
        nombrePerfil.text = user.name
        usuarioEdad.text = "Edad: " + user.age
        usuarioInstitucion.text = "Institución: " + user.institution
        usuarioCorreo.text = "Correo: " + user.email
        if(user.foto != ""){
            profile.visibility = View.INVISIBLE
            progressBar10.visibility = View.VISIBLE
            val localPhotoProfile = Firebase.storage.reference.child("profile_photo/${user.foto}")
            val localFileProfile = File.createTempFile("image", "jpg")
            localPhotoProfile.getFile(localFileProfile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFileProfile.absolutePath)
                profile.setImageBitmap(bitmap)
                progressBar10.visibility = View.INVISIBLE
                profile.visibility = View.VISIBLE
            }
        }
    }

    private fun checkExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
            openGallery()
        else
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == 0)
            openGallery()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startGalleryForResult.launch(intent)
    }

    private val startGalleryForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                imageUri = it.data?.data
                uploadImage()
            }
        }

    private fun uploadImage() {
        if (this.imageUri != null) {
            profile.visibility = View.INVISIBLE
            progressBar10.visibility = View.VISIBLE
            val storageRef = storage.reference
            val reportRef = storageRef.child("profile_photo/" + user.id)
            reportRef.putFile(imageUri!!)
            user.foto = user.id
            db.collection("users").document(user.id).set(user).addOnSuccessListener {
                profile.setImageURI(imageUri)
                profile.visibility = View.VISIBLE
                progressBar10.visibility = View.INVISIBLE
                val newJson = Gson().toJson(user)
                internalMemory.edit().putString("users", newJson).apply()
                out = true
            }
        }
    }
}
