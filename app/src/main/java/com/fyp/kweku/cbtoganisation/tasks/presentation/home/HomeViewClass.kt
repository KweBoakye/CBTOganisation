package com.fyp.kweku.cbtoganisation.tasks.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fyp.kweku.cbtoganisation.databinding.FragmentHomeBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import java.util.Observer

class HomeViewClass(val inflater: LayoutInflater, val parent: ViewGroup?):HomeViewClassInterface {



    private var binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, parent, false)
    private var rootView = binding.root
    private var listener: HomeViewClassInterface.HomeListener? = null
    private lateinit var taskActivity: TaskActivity
    private lateinit var taskViewModel: TaskViewModel
    private val goToCreateNewTaskFragmentButton = binding.goToCreateNewTaskFragmentButton


    init{}

    override fun getRootView(): View {
        return this.rootView
    }

   override fun setGoToCreateNewTaskFragmentButtonOnClickListener(){
        goToCreateNewTaskFragmentButton.setOnClickListener {listener?.onGoToCreateNewTaskFragmentButtonClicked(getTaskActivity()) }
    }



    override fun setListener(listener: HomeViewClassInterface.HomeListener) {
        this.listener = listener
    }

   override fun setTaskActivity(taskActivity: TaskActivity){
        this.taskActivity = taskActivity
   }

    override fun getTaskActivity():TaskActivity{
        return this.taskActivity
    }

    override fun bindTaskViewModel(taskViewModel: TaskViewModel){
        this.taskViewModel = taskViewModel
    }



}