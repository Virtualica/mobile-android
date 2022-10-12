package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.virtualica.mobile_android.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView

class  MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        //re.layoutManager = GridLayoutManager(applicationContext,2)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCreateAccount.setOnClickListener(){
            val intent = Intent(this, RegisterView::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(){
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

        btnLoginGoogle.setOnClickListener(){
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }

    }
}