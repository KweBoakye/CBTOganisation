package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager


import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentTasksByLocationDialogBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

class TasksByLocationViewClass(val inflater: LayoutInflater, val parent: ViewGroup?): TasksByLocationViewClassInterface {



    val tasksByLocationBinding: FragmentTasksByLocationDialogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks_by_location_dialog,parent, false)
    private val root: View = tasksByLocationBinding.root
   val toolbar: Toolbar = tasksByLocationBinding.tasksByLocationToolbar
    val tasksByLocationRecyclerView: RecyclerView = tasksByLocationBinding.tasksByLocationRecyclerView
   private  lateinit var tasksByLocationLayoutManager: LinearLayoutManager
    val tasksByLocationRecyclerAdapter: TasksByLocationRecyclerAdapter = TasksByLocationRecyclerAdapter()
    private lateinit var tasksByLocationViewClassFragmentListener: TasksByLocationViewClassInterface.TasksByLocationViewClassFragmentListener
     private lateinit var location:String



    override fun getRoot(): View =  this.root

    override fun sendDataToAdapter(tasks: List<TaskPresentationModel>)= tasksByLocationRecyclerAdapter.submitList(tasks)

    override fun initRecyclerView(context: Context){

        tasksByLocationLayoutManager = LinearLayoutManager(context, VERTICAL,false)
        with(tasksByLocationRecyclerView){
            layoutManager = tasksByLocationLayoutManager
            adapter = tasksByLocationRecyclerAdapter
        }

    }
    override fun setLocation(location:String?){
        this.location = location!!
    }

    override  fun setFragmentListener(tasksByLocationViewClassFragmentListener: TasksByLocationViewClassInterface.TasksByLocationViewClassFragmentListener){
        this.tasksByLocationViewClassFragmentListener = tasksByLocationViewClassFragmentListener
    }

    override fun setupToolbar(){
        with(toolbar){
            setNavigationIcon(R.drawable.ic_close_white_24dp)
            title = location
            setNavigationOnClickListener {  tasksByLocationViewClassFragmentListener.dismissDialogFragment()}
        }
    }


}