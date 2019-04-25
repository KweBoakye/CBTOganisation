package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import androidx.recyclerview.widget.DiffUtil

class MutableListTaskPresentationModelDiffCallback: DiffUtil.ItemCallback<MutableList<TaskPresentationModel>>() {
    override fun areItemsTheSame(
        oldItem: MutableList<TaskPresentationModel>,
        newItem: MutableList<TaskPresentationModel>
    ): Boolean {
       return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MutableList<TaskPresentationModel>,
        newItem: MutableList<TaskPresentationModel>
    ): Boolean {
        return newItem.containsAll(oldItem)
    }

}