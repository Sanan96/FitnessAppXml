package com.example.newfit.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.newfit.R
import com.example.newfit.adapters.ExerciseModel
import com.example.newfit.databinding.FragmentExerciseBinding
import com.example.newfit.utils.MainViewModel
import com.example.newfit.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable

class ExerciseFragment : Fragment() {
    private lateinit var binding: FragmentExerciseBinding
    private val model: MainViewModel by activityViewModels()
    private var exerciseCounter = 0
    private var exList: ArrayList<ExerciseModel>? = null
    private var timer: CountDownTimer? = null
    private var currentDay = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseCounter = model.getExCount()
        currentDay = model.currentDay
        model.mutableList.observe(viewLifecycleOwner) {
            exList = it
            nextExercise()
        }
        binding.bNext.setOnClickListener {
            nextExercise()
        }
    }

    fun nextExercise() {
        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter++) ?: return
            binding.tvNextName.text = ex.name
            showExercise(ex)
            exType(ex)
            showNextEx()
        } else {
            exerciseCounter++
            FragmentManager.setFragment(
                FinalExerciseFragment.newInstance(),
                activity as AppCompatActivity
            )
        }
    }

    fun exType(ex: ExerciseModel) = with(binding) {
        if (ex.time.startsWith("x")) {
            tvTime.text = ex.time
            timer?.cancel()
            progressBar.progress = 0
        } else {
            setTimer(ex)
        }
    }

    private fun setTimer(ex: ExerciseModel) = with(binding) {
        progressBar.max = ex.time.toInt() * 1000
        timer?.cancel()
        timer = object : CountDownTimer(ex.time.toLong() * 1000, 1) {
            override fun onTick(restTime: Long) {
                progressBar.progress = restTime.toInt()
                tvTime.text = TimeUtils.setTimer(restTime)
            }

            override fun onFinish() {
                nextExercise()
            }

        }.start()
    }

    override fun onPause() {
        super.onPause()
        model.saveData(currentDay.toString(), exerciseCounter - 1)
        timer?.cancel()
    }

    fun showNextEx() = with(binding) {
        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter) ?: return
            imNext.setImageDrawable(GifDrawable(root.context.assets, ex.image))
            tvNextName.text = ex.name + " " + ex.time
            setTimeType(ex)
        } else {
            imNext.setImageDrawable(GifDrawable(root.context.assets, "congrats.gif"))
            tvNextName.text = "Вы завершаете"
        }
    }

    fun showExercise(exercise: ExerciseModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
        tvExcCount.text = "$exerciseCounter" + "/" + "${exList?.size}"
    }

    fun setTimeType(ex: ExerciseModel) {
        if (ex.time.startsWith("x")) {
            binding.tvTime.text = ex.time
        } else {
            val text = ex.name + " " + "${TimeUtils.setTimer(ex.time.toLong() * 1000)}"
            binding.tvNextName.text = text
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }
}