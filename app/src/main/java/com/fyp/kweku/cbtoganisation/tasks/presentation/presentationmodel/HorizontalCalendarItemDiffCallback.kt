package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import androidx.recyclerview.widget.DiffUtil
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem

class HorizontalCalendarItemDiffCallback: DiffUtil.ItemCallback<HorizontalCalendarItem>() {
    override fun areItemsTheSame(oldItem: HorizontalCalendarItem, newItem: HorizontalCalendarItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HorizontalCalendarItem, newItem: HorizontalCalendarItem): Boolean {
        return oldItem.equals(newItem)
    }

}