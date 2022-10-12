package com.virtualica.mobile_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentActivity : AppCompatActivity() {

    private lateinit var navigator:BottomNavigationView
    private lateinit var profile:ImageView
    private lateinit var logo:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)
        showFragment(CategoryFragment.newInstance())


        navigator = findViewById(R.id.navigator)
        showFragment(CategoryView.newInstance())
        navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.book) {
                showFragment(CategoryFragment.newInstance())
            } else if (menuItem.itemId == R.id.simulation) {
                //showFragment(newTaskFragment)
            } else if (menuItem.itemId == R.id.shop) {
                //showFragment(newTaskFragment)
            }
            true
        }

        profile = findViewById(R.id.profile)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        logo = findViewById(R.id.logo)
        logo.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }
    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}