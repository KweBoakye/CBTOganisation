package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.ItemcardViewtasksbydayBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModelDiffCallback
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox

class TasksByLocationRecyclerAdapter(): ListAdapter<TaskPresentationModel, TasksByLocationRecyclerAdapter.TasksByLocationViewHolder>(
    TaskPresentationModelDiffCallback()
) {



    inner class TasksByLocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var taskName: TextView
        lateinit var taskLocation: TextView
        lateinit var taskStartDate: TextView
        lateinit var taskEndDate: TextView
        lateinit var taskStartTime: TextView
        lateinit var taskEndTime: TextView
        lateinit var card: MaterialCardView
        lateinit var taskID: String

        fun bind(taskPresentationModel: TaskPresentationModel) {
            this.taskName.text = taskPresentationModel.taskName
            this.taskLocation.text = taskPresentationModel.taskLocation
            this.taskStartDate.text =
                taskPresentationModel.taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
            this.taskEndDate.text =
                taskPresentationModel.taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
            this.taskStartTime.text = taskPresentationModel.taskStartTime.toString()
            this.taskEndTime.text = taskPresentationModel.taskEndTime.toString()
            this.taskID = taskPresentationModel.taskID
        }
    }

    private lateinit var itemcardViewtasksbydayBinding: ItemcardViewtasksbydayBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksByLocationViewHolder {
        itemcardViewtasksbydayBinding = ItemcardViewtasksbydayBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = TasksByLocationViewHolder(itemcardViewtasksbydayBinding.root)
        viewHolder.taskName  =  itemcardViewtasksbydayBinding.textViewTaskName
        viewHolder.taskLocation = itemcardViewtasksbydayBinding.textViewTaskLocation
        viewHolder.taskStartDate = itemcardViewtasksbydayBinding.textViewTaskStarDate
        viewHolder.taskEndDate =  itemcardViewtasksbydayBinding.textViewTaskEndDate
        viewHolder.taskStartTime = itemcardViewtasksbydayBinding.textviewTaskStartTime
        viewHolder.taskEndTime =  itemcardViewtasksbydayBinding.textviewTaskEndTime
        viewHolder.card = itemcardViewtasksbydayBinding.cardViewTaskItem
        return viewHolder
    }



    override fun onBindViewHolder(holder: TasksByLocationViewHolder, position: Int) {
        holder.bind(getItem((position)))
    }
}