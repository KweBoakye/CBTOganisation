package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import androidx.recyclerview.widget.DiffUtil
import org.threeten.bp.LocalDate

class LocalDateDiffUtilCallback: DiffUtil.ItemCallback<LocalDate>() {
    override fun areItemsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
        return oldItem == (newItem)
    }

    override fun areContentsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}