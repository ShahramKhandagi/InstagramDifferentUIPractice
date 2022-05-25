package com.shahramkhandagi.project.instagramappui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = findNavController(R.id.fragmentContainerView)
        bottomNavigation.setupWithNavController(navHostFragment)

        bottomNavigation.background = null
        bottomNavigation.menu.getItem(2).isEnabled = false
    }
}