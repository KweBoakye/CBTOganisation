package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import androidx.recyclerview.widget.DiffUtil

class HorizontalCalendarItemDiffCallback : DiffUtil.ItemCallback<HorizontalCalendarItem>(){
    override fun areItemsTheSame(oldItem: HorizontalCalendarItem, newItem: HorizontalCalendarItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HorizontalCalendarItem, newItem: HorizontalCalendarItem): Boolean {
        return oldItem == newItem
    }

}