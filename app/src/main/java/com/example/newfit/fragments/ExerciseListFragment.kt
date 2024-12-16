package com.example.newfit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newfit.R
import com.example.newfit.adapters.DaysAdapter
import com.example.newfit.adapters.ExerciseListAdapter
import com.example.newfit.databinding.ExerciseListItemBinding
import com.example.newfit.databinding.FragmentDaysBinding
import com.example.newfit.databinding.FragmentExerciseListBinding
import com.example.newfit.utils.MainViewModel

class ExerciseListFragment : Fragment() {
    private lateinit var binding: FragmentExerciseListBinding
    private lateinit var adapter: ExerciseListAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableList.observe(viewLifecycleOwner) {
        for (i in 0 until model.getExCount()){
            it[i] = it[i].copy(isDone = true)
        }
            adapter.submitList(it)
        }

    }

    fun init() = with(binding) {
        adapter = ExerciseListAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
        bstart.setOnClickListener {
            FragmentManager.setFragment(
                WaitingFragment.newInstance(),
                activity as AppCompatActivity
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExerciseListFragment()
    }
}