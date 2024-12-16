package com.example.newfit.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.newfit.R
import com.example.newfit.databinding.FragmentWaitingBinding
import com.example.newfit.utils.TimeUtils


class WaitingFragment : Fragment() {
    private lateinit var binding: FragmentWaitingBinding
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWaitingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pBar.max = COUNT_DOWN_TIMER.toInt()
        setTimer()
    }

    fun setTimer() = with(binding) {
        timer = object : CountDownTimer(COUNT_DOWN_TIMER, 1) {
            override fun onTick(restTime: Long) {
                pBar.progress = restTime.toInt()
                tvTimer.text = TimeUtils.setTimer(restTime)
            }

            override fun onFinish() {
                FragmentManager.setFragment(
                    ExerciseFragment.newInstance(),
                    activity as AppCompatActivity
                )
            }
        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WaitingFragment()
    }
}

const val COUNT_DOWN_TIMER = 3000L