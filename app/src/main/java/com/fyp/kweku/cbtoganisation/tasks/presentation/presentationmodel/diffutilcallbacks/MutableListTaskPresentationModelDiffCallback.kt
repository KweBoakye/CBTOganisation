package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

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