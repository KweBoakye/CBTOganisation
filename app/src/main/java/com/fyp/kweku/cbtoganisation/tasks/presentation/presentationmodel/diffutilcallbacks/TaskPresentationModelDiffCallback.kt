package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

class TaskPresentationModelDiffCallback: DiffUtil.ItemCallback<TaskPresentationModel>() {
    override fun areItemsTheSame(oldItem: TaskPresentationModel, newItem: TaskPresentationModel): Boolean {
        return oldItem.taskID == newItem.taskID
    }

    override fun areContentsTheSame(oldItem: TaskPresentationModel, newItem: TaskPresentationModel): Boolean {
        return oldItem == newItem
    }
}