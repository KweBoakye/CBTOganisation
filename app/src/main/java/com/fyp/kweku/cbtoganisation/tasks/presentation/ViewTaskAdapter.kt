package com.fyp.kweku.cbtoganisation.tasks.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import kotlinx.android.synthetic.main.cardview_view_tasks.view.*


class ViewTaskAdapter(val tasks : List<TaskPresentationModel>): RecyclerView.Adapter<ViewTaskAdapter.ViewTasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTasksViewHolder {
        return ViewTasksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_view_tasks, parent, false)
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewTasksViewHolder, position: Int) {
       val task = tasks[position]
        holder.view.textViewTaskName.text= task.taskName
        holder.view.textViewTaskLocation.text = task.taskLocation
        holder.view.textViewTaskDate.text = task.taskDate
        holder.view.textViewStartTime.text = task.taskStartTime
        //holder.view.textViewLength.text = task.taskEndTime
    }

    class ViewTasksViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}