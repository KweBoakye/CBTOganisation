package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks

import androidx.recyclerview.widget.DiffUtil
import org.threeten.bp.LocalDate

class MonthCalendarDiffUtilItemCallback:
    DiffUtil.ItemCallback<Triple<LocalDate, Boolean, MutableList<String>>>() {
    override fun areItemsTheSame(
        oldItem: Triple<LocalDate, Boolean, MutableList<String>>,
        newItem: Triple<LocalDate, Boolean, MutableList<String>>
    ): Boolean {
       return newItem.first.isEqual(oldItem.first)
    }

    override fun areContentsTheSame(
        oldItem: Triple<LocalDate, Boolean, MutableList<String>>,
        newItem: Triple<LocalDate, Boolean, MutableList<String>>
    ): Boolean {
        return (oldItem.second == newItem.second && oldItem.third == newItem.third)
    }
}