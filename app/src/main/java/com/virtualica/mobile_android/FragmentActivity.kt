package com.virtualica.mobile_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentActivity : AppCompatActivity() {

    private lateinit var navigator:BottomNavigationView
    //private lateinit var newTaskFragment: NewTaskFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_bar)

        //newTaskFragment = NewTaskFragment.newInstance()

        navigator = findViewById(R.id.navigator)

        navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.book) {
                //showFragment(newTaskFragment)
            } else if (menuItem.itemId == R.id.simulation) {
                //showFragment(newTaskFragment)
            } else if (menuItem.itemId == R.id.shop) {
                //showFragment(newTaskFragment)
            }
            true
        }
    }
    fun showFragment(fragment: Fragment) {
        /*
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
         */
    }
}