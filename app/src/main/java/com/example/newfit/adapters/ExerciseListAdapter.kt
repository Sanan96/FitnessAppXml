package com.example.newfit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newfit.R
import com.example.newfit.databinding.DaysListItemBinding
import com.example.newfit.databinding.ExerciseListItemBinding
import pl.droidsonroids.gif.GifDrawable

class ExerciseListAdapter :ListAdapter<ExerciseModel, ExerciseListAdapter.ViewHolder>(MyComparator()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ExerciseListItemBinding.bind(view)
        fun setData(exercise: ExerciseModel) = with(binding) {
            tvName.text = exercise.name
            tvCount.text = exercise.time
            chB.isChecked = exercise.isDone
            imEx.setImageDrawable(GifDrawable(root.context.assets,exercise.image ))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyComparator : DiffUtil.ItemCallback<ExerciseModel>() {
        override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }
    }
}