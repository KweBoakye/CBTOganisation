package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils

import com.fyp.kweku.cbtoganisation.databinding.ItemcardViewtasksbydayBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.google.android.material.card.MaterialCardView
import timber.log.Timber

class TasksByDayRecyclerAdapter(private val context:  Context): RecyclerView.Adapter< TasksByDayRecyclerAdapter.TasksByDayViewHolder>(){

    private  var data: MutableList<TaskPresentationModel> = mutableListOf<TaskPresentationModel>()
    /*listOf(
       TaskPresentationModel("a",
        "bbbb",
        "here",
        "tasl",
        LocalDate.parse("20/02/2019",ProjectDateTimeUtils.getCustomDateFormatter()),
            LocalDate.parse("20/02/2019",ProjectDateTimeUtils.getCustomDateFormatter()),
            LocalTime.parse("12:20"),
            LocalTime.parse("13:40")))*/

    private lateinit var  viewTasksByDayBinding: ItemcardViewtasksbydayBinding


    class TasksByDayViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      lateinit   var taskName  : TextView
      lateinit   var taskLocation: TextView
      lateinit   var taskStartDate: TextView
      lateinit   var taskEndDate : TextView
      lateinit   var taskStartTime: TextView
      lateinit   var taskEndTime : TextView
      lateinit  var card: MaterialCardView


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksByDayRecyclerAdapter.TasksByDayViewHolder {
        viewTasksByDayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.itemcard_viewtasksbyday,parent, false)
        val tasksByDayViewHolder = TasksByDayRecyclerAdapter.TasksByDayViewHolder(viewTasksByDayBinding.root)
        tasksByDayViewHolder.taskName          = viewTasksByDayBinding.textViewTaskName
        tasksByDayViewHolder.taskLocation = viewTasksByDayBinding.textViewTaskLocation
        tasksByDayViewHolder.taskStartDate = viewTasksByDayBinding.textViewTaskStarDate
        tasksByDayViewHolder.taskEndDate = viewTasksByDayBinding.textViewTaskEndDate
        tasksByDayViewHolder.taskStartTime = viewTasksByDayBinding.textviewTaskStartTime
        tasksByDayViewHolder.taskEndTime = viewTasksByDayBinding.textViewTaskEndDate
        tasksByDayViewHolder.card = viewTasksByDayBinding.cardViewTaskItem
        return tasksByDayViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TasksByDayViewHolder, position: Int) {
        holder.taskName.text = this.data[position].taskName
        holder.taskLocation.text = this.data[position].taskLocation
        holder.taskStartDate.text = this.data[position].taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
        holder.taskStartTime.text = this.data[position].taskStartTime.toString()
        holder.taskEndDate.text = this.data[position].taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter())
        holder.taskEndTime.text = this.data[position].taskEndTime.toString()
        }






    fun setData(data: MutableList<TaskPresentationModel>) {
        this.data = data
    }


}


