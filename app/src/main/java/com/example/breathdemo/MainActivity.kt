package com.example.breathdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.breathdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        findNavController(R.id.fragment).apply {
            setupActionBarWithNavController(this)
        }
        checkRequestPermissions(permissions, this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).popBackStack() || super.onSupportNavigateUp()
    }
}