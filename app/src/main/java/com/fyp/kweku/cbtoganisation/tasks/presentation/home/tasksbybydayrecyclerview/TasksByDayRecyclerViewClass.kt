package com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.FragmentHomeBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.threeten.bp.LocalDate
import timber.log.Timber


class TasksByDayRecyclerViewClass(val context: Context, val view: View,val homeFragment: HomeFragment):TasksByDayRecyclerViewClassInterface
     {
         override fun setTasks(tasks: MutableList<TaskPresentationModel>) {
             noTasksLayout.visibility = View.GONE
             if (tasks.isEmpty()) noTasksLayout.visibility = View.VISIBLE
             tasksByDayRecyclerAdapter.run { setData(tasks)
             notifyDataSetChanged()}
         }


         private val tasksByDayRecycler : RecyclerView
    private var layoutManager :LinearLayoutManager
         private var tasksByDayRecyclerAdapter: TasksByDayRecyclerAdapter
    val noTasksLayout= view.empty_text

    /*val tasksByDayObserver = Observer<MutableList<TaskPresentationModel>> {tasks ->
        noTasksLayout.visibility = View.GONE
        tasksByDayRecyclerAdapter.setData(tasks)
        if (tasks.isEmpty()) noTasksLayout.visibility = View.VISIBLE}*/


    init {
        tasksByDayRecycler = view.tasks_by_day_recycler
        layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        tasksByDayRecycler.layoutManager = layoutManager

        tasksByDayRecyclerAdapter = TasksByDayRecyclerAdapter(context)

        tasksByDayRecycler.adapter = tasksByDayRecyclerAdapter


    }



/*fun setObserver(){

    taskViewModel.tasksByDay.observe(viewLifecycleOwner,tasksByDayObserver)
}

    fun unSetObserver(){
        taskViewModel.tasksByDay.removeObserver(tasksByDayObserver)}*/



 /*fun onDaySelected(date: String){
    val dateAsLocalDate = LocalDate.parse(date,ProjectDateTimeUtils.getCustomDateFormatter())
    Timber.i("$date , ${dateAsLocalDate.format(ProjectDateTimeUtils.getCustomDateFormatter())} is null")
    if (tasksByDayRecyclerAdapter.getAllTasks() == null){ noTasksLayout.visibility = View.VISIBLE
    }
    else{
    val filteredTaskList = tasksByDayRecyclerAdapter.getAllTasks()!!.filter {task -> ProjectDateTimeUtils.checkIfDateIsInRange(dateAsLocalDate,task.taskStartDate, task.taskEndDate) }
        Timber.i("{${tasksByDayRecyclerAdapter.getAllTasks()}")
    val sortedList = filteredTaskList.sortedWith(compareBy({it.taskStartDate},{it.taskStartTime}))
        Timber.i("$sortedList")
    tasksByDayRecyclerAdapter.setData(sortedList)
    noTasksLayout.visibility = View.GONE}
}*/





}