package com.example.newfit.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfit.adapters.ExerciseModel

class MainViewModel : ViewModel() {
    val mutableList = MutableLiveData<ArrayList<ExerciseModel>>()
    var pref: SharedPreferences? = null
    var currentDay = 0

    fun saveData(key: String, value: Int) {
        pref?.edit()?.putInt(key, value)?.apply()
    }

    fun getExCount(): Int {
        return pref?.getInt(currentDay.toString(), 0) ?: 0
    }
}