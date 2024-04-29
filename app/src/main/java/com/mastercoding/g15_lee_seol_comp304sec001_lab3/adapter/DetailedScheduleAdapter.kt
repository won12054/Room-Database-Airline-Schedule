package com.mastercoding.g15_lee_seol_comp304sec001_lab3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule.Schedule
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.databinding.DetailedItemBinding

class DetailedScheduleAdapter(private val onItemClicked: (Schedule) -> Unit)
    : ListAdapter<Schedule, DetailedScheduleAdapter.DetailedScheduleViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedScheduleViewHolder {
        val viewHolder = DetailedScheduleViewHolder(
            DetailedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DetailedScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DetailedScheduleViewHolder(private var binding: DetailedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: Schedule) {
            val arrivalTimeFormatted = String.format("%04d", schedule.arrivalTime)
            val formattedTime = "${arrivalTimeFormatted.substring(0, 2)}:${arrivalTimeFormatted.substring(2, 4)}"

            binding.arrivalTimeDetailTextView.text = formattedTime

            binding.terminalNumberDetailTextView.text = schedule.terminalNumber
            binding.statusDetailTextView.text = schedule.status
        }
    }

}




