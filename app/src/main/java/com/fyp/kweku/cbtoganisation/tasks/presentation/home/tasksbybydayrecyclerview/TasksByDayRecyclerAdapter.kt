package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils

import com.fyp.kweku.cbtoganisation.databinding.ItemcardViewtasksbydayBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModelDiffCallback
import com.google.android.material.card.MaterialCardView


class TasksByDayRecyclerAdapter(private val context:  Context, val tasksByDayAdapterListener: TasksByDayAdpterListener): ListAdapter<TaskPresentationModel,
        TasksByDayRecyclerAdapter.TasksByDayViewHolder>(TaskPresentationModelDiffCallback()){

    private  var data: MutableList<TaskPresentationModel> = mutableListOf<TaskPresentationModel>()

    private lateinit var  viewTasksByDayBinding: ItemcardViewtasksbydayBinding


    inner class TasksByDayViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      lateinit   var taskName  : TextView
      lateinit   var taskLocation: TextView
      lateinit   var taskStartDate: TextView
      lateinit   var taskEndDate : TextView
      lateinit   var taskStartTime: TextView
      lateinit   var taskEndTime : TextView
      lateinit  var card: MaterialCardView
        lateinit var taskID: String

        fun bind(taskPresentationModel: TaskPresentationModel){
            this.taskName.text=  taskPresentationModel.taskName
            this.taskLocation.text= taskPresentationModel.taskLocation
            this.taskStartDate.text= taskPresentationModel.taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
            this.taskEndDate.text= taskPresentationModel.taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
            this.taskStartTime.text= taskPresentationModel.taskStartTime.toString()
            this.taskEndTime.text= taskPresentationModel.taskEndTime.toString()
            this.taskID = taskPresentationModel.taskID
            this.card.setOnClickListener { tasksByDayAdapterListener.taskSelected(taskID) }
        }


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksByDayViewHolder {
        viewTasksByDayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.itemcard_viewtasksbyday,parent, false)
        val tasksByDayViewHolder =TasksByDayViewHolder(viewTasksByDayBinding.root)
        with(tasksByDayViewHolder){
            taskName          = viewTasksByDayBinding.textViewTaskName
            taskLocation = viewTasksByDayBinding.textViewTaskLocation
            taskStartDate = viewTasksByDayBinding.textViewTaskStarDate
            taskEndDate = viewTasksByDayBinding.textViewTaskEndDate
            taskStartTime = viewTasksByDayBinding.textviewTaskStartTime
            taskEndTime = viewTasksByDayBinding.textviewTaskEndTime
            card = viewTasksByDayBinding.cardViewTaskItem
        }
        /*tasksByDayViewHolder.taskName          = viewTasksByDayBinding.textViewTaskName
        tasksByDayViewHolder.taskLocation = viewTasksByDayBinding.textViewTaskLocation
        tasksByDayViewHolder.taskStartDate = viewTasksByDayBinding.textViewTaskStarDate
        tasksByDayViewHolder.taskEndDate = viewTasksByDayBinding.textViewTaskEndDate
        tasksByDayViewHolder.taskStartTime = viewTasksByDayBinding.textviewTaskStartTime
        tasksByDayViewHolder.taskEndTime = viewTasksByDayBinding.textviewTaskEndTime
        tasksByDayViewHolder.card = viewTasksByDayBinding.cardViewTaskItem*/
        return tasksByDayViewHolder
    }

   /* override fun getItemCount(): Int {
        return data.size
    }*/

    override fun onBindViewHolder(holder: TasksByDayViewHolder, position: Int) {
        /* holder.taskName.text = this.data[position].taskName
         holder.taskLocation.text = this.data[position].taskLocation
         holder.taskStartDate.text = this.data[position].taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
         holder.taskStartTime.text = this.data[position].taskStartTime.toString()
         holder.taskEndDate.text = this.data[position].taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
         holder.taskEndTime.text = this.data[position].taskEndTime.toString()
         holder.taskCompleted.isChecked = this.data[position].taskCompleted*/
        holder.bind(getItem(position))


        }


   override fun submitList(list: MutableList<TaskPresentationModel>?) {
        super.submitList(if (list != null) list else null)
    }



    fun setData(data: MutableList<TaskPresentationModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    interface TasksByDayAdpterListener{
        fun taskSelected(taskID: String)
    }

}


