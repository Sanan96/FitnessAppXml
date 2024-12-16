package com.example.newfit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.newfit.fragments.DaysFragment
import com.example.newfit.fragments.FragmentManager
import com.example.newfit.utils.MainViewModel

class MainActivity : AppCompatActivity() {
    private val model:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
        model.pref =getSharedPreferences("main", MODE_PRIVATE)
    }
}