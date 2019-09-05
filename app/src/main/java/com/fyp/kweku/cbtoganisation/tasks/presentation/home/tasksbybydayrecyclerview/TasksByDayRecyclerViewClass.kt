package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.android.synthetic.main.fragment_home.view.*



class TasksByDayRecyclerViewClass(val context: Context, val view: View,homeFragment: HomeFragment):TasksByDayRecyclerViewClassInterface, TasksByDayRecyclerAdapter.TasksByDayAdpterListener
     {
         override fun deleteTask(taskID: String) {
             tasksByDayRecyclerViewClassListener.deleteTask(taskID)
         }

         override fun setTasks(tasks: MutableList<TaskPresentationModel>) {
             noTasksLayout.visibility = View.GONE
             tasksByDayRecyclerAdapter.submitList(tasks.toMutableList())
             if (tasks.isEmpty()) noTasksLayout.visibility = View.VISIBLE


         }

         override fun taskSelected(taskID: String){
             fragmentListener.launchDialog(taskID)
         }


         private val tasksByDayRecycler : RecyclerView
    private var layoutManager :LinearLayoutManager

         private var tasksByDayRecyclerAdapter: TasksByDayRecyclerAdapter
         private val fragmentListener: TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassFragmentListener = homeFragment
         private lateinit var tasksByDayRecyclerViewClassListener: TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassListener

    val noTasksLayout= view.empty_text

    /*val tasksByDayObserver = Observer<MutableList<TaskPresentationModel>> {tasks ->
        noTasksLayout.visibility = View.GONE
        tasksByDayRecyclerAdapter.setData(tasks)
        if (tasks.isEmpty()) noTasksLayout.visibility = View.VISIBLE}*/


    init {

        tasksByDayRecycler = view.tasks_by_day_recycler
        layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        tasksByDayRecycler.layoutManager = layoutManager

        tasksByDayRecyclerAdapter = TasksByDayRecyclerAdapter(context, this)

        tasksByDayRecycler.adapter = tasksByDayRecyclerAdapter

    }

        override fun setTasksByDayRecyclerViewClassListener(tasksByDayRecyclerViewClassListener: TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassListener){
             this.tasksByDayRecyclerViewClassListener = tasksByDayRecyclerViewClassListener
         }


         /*override fun setFragmentListener(fragmentListener: TasksByDayRecyclerViewClassInterface.TasksByDayRecyclerViewClassFragmentListener){
             this.fragmentListener = fragmentListener
         }*/







}