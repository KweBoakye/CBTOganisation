package com.fyp.kweku.cbtoganisation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

class ViewTaskAdapter(val tasks : List<Task>): RecyclerView.Adapter<ViewTaskAdapter.ViewTasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTasksViewHolder {
        return ViewTasksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_view_tasks, parent, false)
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewTasksViewHolder, position: Int) {
       val task = tasks[position]
        holder.view.
    }

    class ViewTasksViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}