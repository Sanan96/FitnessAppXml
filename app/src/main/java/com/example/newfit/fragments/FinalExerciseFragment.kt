package com.example.newfit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.newfit.R
import com.example.newfit.adapters.ExerciseModel
import com.example.newfit.databinding.FragmentFinalExerciseBinding
import pl.droidsonroids.gif.GifDrawable

class FinalExerciseFragment : Fragment() {
    private lateinit var binding:FragmentFinalExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinalExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPhoto()
        binding.button.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
        }
    }


    fun setPhoto() = with(binding){
        imageView3.setImageDrawable(GifDrawable(root.context.assets, "congrats.gif" ))
    }


    companion object {
        @JvmStatic
        fun newInstance() = FinalExerciseFragment()
    }
}
