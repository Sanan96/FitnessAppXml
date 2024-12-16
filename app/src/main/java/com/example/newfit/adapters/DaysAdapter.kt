package com.example.newfit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newfit.R
import com.example.newfit.databinding.DaysListItemBinding

class DaysAdapter(val listener: Listener) :
    ListAdapter<DayModel, DaysAdapter.ViewHolder>(MyComparator()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DaysListItemBinding.bind(view)
        fun setData(day: DayModel, listener: Listener) = with(binding) {
            val name = root.context.getString(R.string.days) + "${adapterPosition + 1}"
            tvName.text = name
            val count =
                day.exercise.split(",").size.toString() + " " + root.context.getString(R.string.exercisess)
            tvExCounter.text = count
            checkBox.isChecked = day.isDone
            itemView.setOnClickListener {
                listener.onClick(day.copy(dayNumber = adapterPosition + 1))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.days_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class MyComparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onClick(day: DayModel)
    }
}