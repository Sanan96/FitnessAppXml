package com.example.newfit.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfit.adapters.DayModel
import com.example.newfit.R
import com.example.newfit.adapters.DaysAdapter
import com.example.newfit.adapters.ExerciseModel
import com.example.newfit.databinding.FragmentDaysBinding
import com.example.newfit.utils.DialogManager
import com.example.newfit.utils.MainViewModel


class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding
    private lateinit var adapter: DaysAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.currentDay = 0
        init()
        binding.reset.setOnClickListener {
            DialogManager.showDialog(
                activity as AppCompatActivity,
                R.string.reset_days_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.pref?.edit()?.clear()?.apply()
                        adapter.submitList(fillDaysAdapter())
                    }
                }
            )
        }
    }

    fun init() = with(binding) {
        adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysAdapter())
    }

    @SuppressLint("SuspiciousIndentation")
    private fun fillDaysAdapter(): ArrayList<DayModel> {
        var daysDoneCounter = 0
        val temp = ArrayList<DayModel>()
        resources.getStringArray(R.array.exerc_by_days).forEach {
            model.currentDay++
            val exCounter = it.split(",").size
            temp.add(DayModel(it, 0, model.getExCount() == exCounter))
        }
        binding.pb.max = temp.size
        temp.forEach {
            if (it.isDone) daysDoneCounter++

            updateUI(temp.size - daysDoneCounter, temp.size)
        }
        return temp
    }

    fun updateUI(restDays: Int, allDays: Int) = with(binding) {
        val rDays = getString(R.string.rest) + " " + restDays + getString(R.string.rest_days)
        tvRestDays.text = rDays
        pb.progress = allDays - restDays
    }


    private fun fillExerciseArray(day: DayModel) {
        val temp = ArrayList<ExerciseModel>()
        day.exercise.split(",").forEach {
            val ex = resources.getStringArray(R.array.exercises_info)
            val exl = ex[it.toInt()]
            val e = exl.split("|")
            temp.add(ExerciseModel(e[0], e[1], false, e[2]))
        }
        model.mutableList.value = temp
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(day: DayModel) {
        if (!day.isDone) {
            fillExerciseArray(day)
            model.currentDay = day.dayNumber
            FragmentManager.setFragment(
                ExerciseListFragment.newInstance(),
                activity as AppCompatActivity
            )
        } else {
            DialogManager.showDialog(
                activity as AppCompatActivity,
                R.string.reset_day_message,
                object : DialogManager.Listener {
                    override fun onClick() {
                        model.saveData(day.dayNumber.toString(), 0)
                        fillExerciseArray(day)
                        model.currentDay = day.dayNumber
                        FragmentManager.setFragment(
                            ExerciseListFragment.newInstance(),
                            activity as AppCompatActivity
                        )
                    }
                }
            )
        }
    }
}