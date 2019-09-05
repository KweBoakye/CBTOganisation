package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks

import androidx.recyclerview.widget.DiffUtil

class MonthViewDayDiffUtilItemCallback:DiffUtil.ItemCallback<MonthViewDay>() {
    override fun areItemsTheSame(oldItem: MonthViewDay, newItem: MonthViewDay): Boolean {
        return oldItem.date.isEqual(newItem.date)
    }

    override fun areContentsTheSame(oldItem: MonthViewDay, newItem: MonthViewDay): Boolean {
        return (oldItem.partOfCurrentMonth == newItem.partOfCurrentMonth && oldItem.taskList == newItem.taskList)
    }
}