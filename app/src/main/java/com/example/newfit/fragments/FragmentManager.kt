package com.example.newfit.fragments


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newfit.R

object FragmentManager {
    private var currentFragment: Fragment? = null
    fun setFragment(newFragment: Fragment, act: AppCompatActivity ){

        val transaction = act.supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        transaction.replace(R.id.place_holder, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        currentFragment = newFragment
    }
}