package com.fyp.kweku.cbtoganisation.tasks.presentation.home

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity

class HomeController:HomeViewClassInterface.HomeListener {


    private lateinit var homeViewClassInterface:HomeViewClassInterface

    override fun onGoToCreateNewTaskFragmentButtonClicked(taskActivity: TaskActivity) {
        taskActivity.navController.navigate(R.id.action_homeFragment_to_createNewTaskFragment) }

fun bindView(homeViewClassInterface: HomeViewClassInterface){
    this.homeViewClassInterface = homeViewClassInterface

}
    fun onCreateView(taskActivity: TaskActivity){homeViewClassInterface.setTaskActivity(taskActivity)}

    fun onviewCreated(){homeViewClassInterface.setListener(this)
    homeViewClassInterface.setgoToCreateNewTaskFragmentButtonOnClickListener()}

}




