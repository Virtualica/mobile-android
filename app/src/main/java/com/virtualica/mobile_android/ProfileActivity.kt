package com.virtualica.mobile_android

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.ktx.storage
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile.*
import kotlin.math.log

class ProfileActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private val REQUEST_PERMISSION = 100
    private var imageUri: Uri? = null
    private val db = Firebase.firestore
    private val storage = Firebase.storage

    private lateinit var profile: ImageView
    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        logo = findViewById(R.id.logo)
        logo.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

        profile = findViewById(R.id.profile)
        profile.setOnClickListener {
            checkExternalStoragePermission()
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
                uploadImage { imageUrl ->
                    Log.e("Error", "URL2$imageUrl")
                    if (imageUrl != null) {
                        Picasso.get().load(Uri.parse(imageUrl)).into(profile)
                    }
                }
                Log.e("Error", "URL" + imageUri.toString())
            }
        }

    private fun uploadImage(completion: (String?) -> Unit) {
        if (this.imageUri != null) {
            val storageRef = storage.reference
            val reportRef = storageRef.child("profile_photo/test.jpg")
            val uploadTask = reportRef.putFile(imageUri!!)
            uploadTask.addOnFailureListener {
                completion(null)
            }.addOnSuccessListener {
                reportRef.downloadUrl
                    .addOnSuccessListener {
                        completion(it.toString())
                    }
                    .addOnCanceledListener {
                        completion(null)
                    }
            }
        } else {
            completion(null)
        }
    }
}
