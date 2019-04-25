package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import androidx.recyclerview.widget.DiffUtil
import org.threeten.bp.YearMonth

class YearMonthDiffUtil: DiffUtil.ItemCallback<YearMonth>() {
    override fun areItemsTheSame(oldItem: YearMonth, newItem: YearMonth): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: YearMonth, newItem: YearMonth): Boolean {
       return oldItem.toString() == newItem.toString()
    }
}