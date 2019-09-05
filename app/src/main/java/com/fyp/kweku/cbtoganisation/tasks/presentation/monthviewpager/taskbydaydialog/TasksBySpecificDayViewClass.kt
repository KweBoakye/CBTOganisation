package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.FragmentTaskBySpecificDayDialogBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

class TasksBySpecificDayViewClass( val inflater: LayoutInflater, val parent: ViewGroup?): TasksBySpecificDayViewClassInterface{

    val tasksBySpecificDayBinding : FragmentTaskBySpecificDayDialogBinding = FragmentTaskBySpecificDayDialogBinding.inflate(inflater, parent, false)
    private  val root: View = tasksBySpecificDayBinding.root
    val  toolbar = tasksBySpecificDayBinding.tasksBySpecificDayToolbar
    val tasksBySpecificDayRecyclerView: RecyclerView= tasksBySpecificDayBinding.tasksBySpecificDayRecyclerView
    val tasksBySpecificDayAdapter: TasksBySpecificDayAdapter = TasksBySpecificDayAdapter()
    private  lateinit var tasksBySpecificDayLayoutManager: LinearLayoutManager
    private lateinit var date:LocalDate
   private lateinit var tasksBySpecificDayViewClassFragmentListener: TasksBySpecificDayViewClassInterface.TaskBySpecificDayViewClassFragmentListener

    override fun getRoot(): View =  this.root

    override fun sendDataToAdapter(tasks: List<TaskPresentationModel>)= tasksBySpecificDayAdapter.submitList(tasks)

    override fun initRecyclerView(context: Context){
        tasksBySpecificDayLayoutManager  = LinearLayoutManager(context, VERTICAL, false)
        with(tasksBySpecificDayRecyclerView){
            layoutManager = tasksBySpecificDayLayoutManager
            adapter = tasksBySpecificDayAdapter
        }

    }

    override fun setupToolbar() {
        with(toolbar) {
            setNavigationIcon(R.drawable.ic_close_white_24dp)

            title =  with(date){ "$dayOfWeek $dayOfMonth $month $year" } //date.format(ProjectDateTimeUtils.getCustomDateFormatter())
            setNavigationOnClickListener { tasksBySpecificDayViewClassFragmentListener.dismissDialogFragment() }
        }
    }

    override fun setDate(date: LocalDate?){
        this.date = date!!
    }

    override fun setFragmentListener(taskBySpecificDayViewClassFragmentListener: TasksBySpecificDayViewClassInterface.TaskBySpecificDayViewClassFragmentListener){
        this.tasksBySpecificDayViewClassFragmentListener = taskBySpecificDayViewClassFragmentListener
    }
}