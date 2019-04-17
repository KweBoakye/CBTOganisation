package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import androidx.recyclerview.widget.DiffUtil

class TaskPresentationModelDiffCallback: DiffUtil.ItemCallback<TaskPresentationModel>() {
    override fun areItemsTheSame(oldItem: TaskPresentationModel, newItem: TaskPresentationModel): Boolean {
        return oldItem.taskID == newItem.taskID
    }

    override fun areContentsTheSame(oldItem: TaskPresentationModel, newItem: TaskPresentationModel): Boolean {
        return oldItem == newItem
    }
}